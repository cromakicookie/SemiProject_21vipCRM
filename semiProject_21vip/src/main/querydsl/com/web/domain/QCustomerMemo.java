package com.web.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCustomerMemo is a Querydsl query type for CustomerMemo
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCustomerMemo extends EntityPathBase<CustomerMemo> {

    private static final long serialVersionUID = -275723205L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCustomerMemo customerMemo = new QCustomerMemo("customerMemo");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final StringPath createBy = _super.createBy;

    //inherited
    public final DateTimePath<java.util.Date> createDate = _super.createDate;

    public final QCustomer customer;

    public final StringPath memoContent = createString("memoContent");

    public final NumberPath<Integer> memoLevel = createNumber("memoLevel", Integer.class);

    public final NumberPath<Long> MemoNum = createNumber("MemoNum", Long.class);

    //inherited
    public final StringPath modifyBy = _super.modifyBy;

    //inherited
    public final DateTimePath<java.util.Date> modifyDate = _super.modifyDate;

    public QCustomerMemo(String variable) {
        this(CustomerMemo.class, forVariable(variable), INITS);
    }

    public QCustomerMemo(Path<? extends CustomerMemo> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCustomerMemo(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCustomerMemo(PathMetadata metadata, PathInits inits) {
        this(CustomerMemo.class, metadata, inits);
    }

    public QCustomerMemo(Class<? extends CustomerMemo> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.customer = inits.isInitialized("customer") ? new QCustomer(forProperty("customer")) : null;
    }

}

