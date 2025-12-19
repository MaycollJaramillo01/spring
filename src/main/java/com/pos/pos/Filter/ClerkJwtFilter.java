package com.pos.pos.Filter;

import com.auth0.jwk.Jwk;
import com.auth0.jwk.JwkProvider;
import com.auth0.jwk.JwkProviderBuilder;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.security.interfaces.RSAPublicKey;
import java.util.Collections;

import org.springframework.security.core.context.SecurityContextHolder;

@Component
public class ClerkJwtFilter implements Filter {

	private static final String CLERK_JWT_HEADER = "Authorization";

	@Value("${clerk.issuer}")
	private String clerkIssuer;

	private JwkProvider provider;

	@PostConstruct
	public void init() {
		// Initialize the JwkProvider with Clerk's JWKS endpoint
		provider = new JwkProviderBuilder(clerkIssuer)
				.build();
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		var httpRequest = (HttpServletRequest) request;
		var token = extractToken(httpRequest);

		if (token != null) {
			try {

				String kid = JWT.decode(token).getKeyId(); // Extract "kid" from JWT header
				Jwk jwk = provider.get(kid);
				Algorithm algorithm = Algorithm.RSA256((RSAPublicKey) jwk.getPublicKey(), null);
				DecodedJWT jwt = JWT.require(algorithm)
						.withIssuer(clerkIssuer)
						.acceptLeeway(60) // Allow some leeway for clock skew
						.build()
						.verify(token);

				// Puedes setearlo en un contexto o SecurityContextHolder si deseas.
				//
				SecurityContextHolder.getContext().setAuthentication(
						new UsernamePasswordAuthenticationToken(
								jwt.getSubject(), // principal
								null, // credentials
								Collections.emptyList() // authorities
						));
			} catch (Exception e) {
				System.err.println("JWT validation failed: " + e.getMessage());
				((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED,
						"Invalid JWT");
				return;
			}
		}

		chain.doFilter(request, response);
	}

	private String extractToken(HttpServletRequest request) {
		var authHeader = request.getHeader(CLERK_JWT_HEADER);
		if (authHeader != null && authHeader.startsWith("Bearer ")) {
			return authHeader.substring(7);
		}
		return null;
	}

}
