package com.guildkeeper.backend.dao.map;

import com.guildkeeper.backend.model.map.MapAnnotation;

import java.util.List;

public interface MapAnnotationDao {
    List<MapAnnotation> getAnnotationsForTile(int tileId, int userId, boolean includePrivate);
    void addAnnotation(MapAnnotation annotation);
    void deleteAnnotation(int annotationId);
}
