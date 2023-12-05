package com.web.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QLuxury is a Querydsl query type for Luxury
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLuxury extends EntityPathBase<Luxury> {

    private static final long serialVersionUID = -1637559472L;

    public static final QLuxury luxury = new QLuxury("luxury");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final StringPath createBy = _super.createBy;

    //inherited
    public final DateTimePath<java.util.Date> createDate = _super.createDate;

    public final NumberPath<Long> customerNum = createNumber("customerNum", Long.class);

    public final StringPath luxuryBrandName = createString("luxuryBrandName");

    public final StringPath luxuryDate = createString("luxuryDate");

    public final NumberPath<Long> luxuryHeadCount = createNumber("luxuryHeadCount", Long.class);

    public final StringPath luxuryName = createString("luxuryName");

    public final StringPath luxuryPhone = createString("luxuryPhone");

    public final NumberPath<Long> luxurySeq = createNumber("luxurySeq", Long.class);

    public final StringPath luxuryTime = createString("luxuryTime");

    //inherited
    public final StringPath modifyBy = _super.modifyBy;

    //inherited
    public final DateTimePath<java.util.Date> modifyDate = _super.modifyDate;

    public QLuxury(String variable) {
        super(Luxury.class, forVariable(variable));
    }

    public QLuxury(Path<? extends Luxury> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLuxury(PathMetadata metadata) {
        super(Luxury.class, metadata);
    }

}

