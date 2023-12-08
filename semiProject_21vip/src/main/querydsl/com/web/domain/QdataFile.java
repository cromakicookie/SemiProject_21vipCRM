package com.web.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QdataFile is a Querydsl query type for dataFile
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QdataFile extends EntityPathBase<dataFile> {

    private static final long serialVersionUID = 731605929L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QdataFile dataFile = new QdataFile("dataFile");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final QCalendar calendar;

    //inherited
    public final StringPath createBy = _super.createBy;

    //inherited
    public final DateTimePath<java.util.Date> createDate = _super.createDate;

    public final StringPath fileName = createString("fileName");

    public final NumberPath<Long> fileNumber = createNumber("fileNumber", Long.class);

    public final StringPath fileRoot = createString("fileRoot");

    //inherited
    public final StringPath modifyBy = _super.modifyBy;

    //inherited
    public final DateTimePath<java.util.Date> modifyDate = _super.modifyDate;

    public QdataFile(String variable) {
        this(dataFile.class, forVariable(variable), INITS);
    }

    public QdataFile(Path<? extends dataFile> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QdataFile(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QdataFile(PathMetadata metadata, PathInits inits) {
        this(dataFile.class, metadata, inits);
    }

    public QdataFile(Class<? extends dataFile> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.calendar = inits.isInitialized("calendar") ? new QCalendar(forProperty("calendar"), inits.get("calendar")) : null;
    }

}

