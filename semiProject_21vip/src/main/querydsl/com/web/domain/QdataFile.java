package com.web.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QdataFile is a Querydsl query type for dataFile
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QdataFile extends EntityPathBase<dataFile> {

    private static final long serialVersionUID = 731605929L;

    public static final QdataFile dataFile = new QdataFile("dataFile");

    public final QBaseEntity _super = new QBaseEntity(this);

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
        super(dataFile.class, forVariable(variable));
    }

    public QdataFile(Path<? extends dataFile> path) {
        super(path.getType(), path.getMetadata());
    }

    public QdataFile(PathMetadata metadata) {
        super(dataFile.class, metadata);
    }

}

