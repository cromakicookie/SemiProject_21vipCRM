package com.web.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QVoucherC is a Querydsl query type for VoucherC
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QVoucherC extends EntityPathBase<VoucherC> {

    private static final long serialVersionUID = 1673700056L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QVoucherC voucherC = new QVoucherC("voucherC");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final StringPath CheckedVoucherCode = createString("CheckedVoucherCode");

    //inherited
    public final StringPath createBy = _super.createBy;

    //inherited
    public final DateTimePath<java.util.Date> createDate = _super.createDate;

    public final QCustomer customer;

    //inherited
    public final StringPath modifyBy = _super.modifyBy;

    //inherited
    public final DateTimePath<java.util.Date> modifyDate = _super.modifyDate;

    public final DateTimePath<java.util.Date> voucherExdate = createDateTime("voucherExdate", java.util.Date.class);

    public final DateTimePath<java.util.Date> voucherIssuedDate = createDateTime("voucherIssuedDate", java.util.Date.class);

    public QVoucherC(String variable) {
        this(VoucherC.class, forVariable(variable), INITS);
    }

    public QVoucherC(Path<? extends VoucherC> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QVoucherC(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QVoucherC(PathMetadata metadata, PathInits inits) {
        this(VoucherC.class, metadata, inits);
    }

    public QVoucherC(Class<? extends VoucherC> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.customer = inits.isInitialized("customer") ? new QCustomer(forProperty("customer")) : null;
    }

}

