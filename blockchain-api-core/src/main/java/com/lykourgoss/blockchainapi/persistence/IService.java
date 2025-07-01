package com.lykourgoss.blockchainapi.persistence;

import java.util.List;

public interface IService<TEntity, TId> {
    TEntity add(TEntity entity);

    TEntity get(TId id);

    List<TEntity> getAll();

    TEntity update(TId id, TEntity entity);

    TEntity delete(TId id);
}
