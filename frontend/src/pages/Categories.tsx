import { useState } from 'react';
import { useForm } from 'react-hook-form';
import { z } from 'zod';
import { zodResolver } from '@hookform/resolvers/zod';
import { useMutation, useQuery, useQueryClient } from '@tanstack/react-query';
import { http } from '@/lib/http';
import type { Category } from '@/types/domain';
import { DataTable } from '@/components/DataTable';
import styles from './Categories.module.css';
import { buildFormBody } from '@/lib/forms';

const categorySchema = z.object({
  name: z.string().min(3, 'El nombre es obligatorio'),
  description: z.string().optional()
});

type CategoryFormValues = z.infer<typeof categorySchema>;

export default function Categories() {
  const [editing, setEditing] = useState<Category | null>(null);
  const queryClient = useQueryClient();
  const {
    register,
    handleSubmit,
    reset,
    formState: { errors }
  } = useForm<CategoryFormValues>({
    resolver: zodResolver(categorySchema)
  });

  const categoriesQuery = useQuery({
    queryKey: ['categories'],
    queryFn: async () => {
      const response = await http.get<Category[]>('/api/categories');
      return response.data;
    }
  });

  const upsertMutation = useMutation({
    mutationFn: async (values: CategoryFormValues) => {
      const body = buildFormBody(values);
      if (editing) {
        const response = await http.put<Category>(`/api/categories/${editing.id}`, body, {
          headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
        });
        return response.data;
      }
      const response = await http.post<Category>('/api/categories', body, {
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
      });
      return response.data;
    },
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['categories'] });
      reset();
      setEditing(null);
    }
  });

  const deleteMutation = useMutation({
    mutationFn: async (id: number) => {
      await http.delete(`/api/categories/${id}`);
    },
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['categories'] });
      if (editing?.id) {
        setEditing(null);
        reset();
      }
    }
  });

  const onSubmit = (values: CategoryFormValues) => {
    upsertMutation.mutate(values);
  };

  return (
    <div className={styles.wrapper}>
      <div className={styles.header}>
        <h2>Categorías</h2>
      </div>
      <div className={styles.grid}>
        <section className={styles.card}>
          <DataTable
            data={categoriesQuery.data ?? []}
            isLoading={categoriesQuery.isFetching}
            emptyState="Aún no hay categorías"
            columns={[
              { key: 'name', header: 'Nombre' },
              { key: 'description', header: 'Descripción' },
              {
                key: 'createdAt',
                header: 'Creada',
                render: (category) =>
                  category.createdAt ? new Date(category.createdAt).toLocaleDateString('es-NI') : '—'
              },
              {
                key: 'actions',
                header: 'Acciones',
                render: (category) => (
                  <div className={styles.actions}>
                    <button type="button" onClick={() => {
                      setEditing(category);
                      reset({ name: category.name, description: category.description });
                    }}>
                      Editar
                    </button>
                    <button
                      type="button"
                      onClick={() => {
                        if (window.confirm('¿Eliminar categoría?')) {
                          deleteMutation.mutate(category.id);
                        }
                      }}
                    >
                      Eliminar
                    </button>
                  </div>
                )
              }
            ]}
          />
        </section>
        <section className={styles.card}>
          <h3>{editing ? 'Editar categoría' : 'Crear categoría'}</h3>
          <form className={styles.form} onSubmit={handleSubmit(onSubmit)}>
            <div>
              <label>Nombre</label>
              <input type="text" {...register('name')} placeholder="Ej. Lácteos" />
              {errors.name && <span className={styles.error}>{errors.name.message}</span>}
            </div>
            <div>
              <label>Descripción</label>
              <textarea rows={3} {...register('description')} placeholder="Opcional" />
            </div>
            <button type="submit" disabled={upsertMutation.isPending}>
              {editing ? 'Actualizar' : 'Crear'}
            </button>
            {editing && (
              <button
                type="button"
                onClick={() => {
                  setEditing(null);
                  reset({ name: '', description: '' });
                }}
              >
                Cancelar edición
              </button>
            )}
          </form>
        </section>
      </div>
    </div>
  );
}
