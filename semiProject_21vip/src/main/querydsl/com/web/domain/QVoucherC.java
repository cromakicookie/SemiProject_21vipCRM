package com.web.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QVoucherC is a Querydsl query type for VoucherC
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QVoucherC extends EntityPathBase<VoucherC> {

    private static final long serialVersionUID = 1673700056L;

    public static final QVoucherC voucherC = new QVoucherC("voucherC");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final StringPath createBy = _super.createBy;

    //inherited
    public final DateTimePath<java.util.Date> createDate = _super.createDate;

    //inherited
    public final StringPath modifyBy = _super.modifyBy;

    //inherited
    public final DateTimePath<java.util.Date> modifyDate = _super.modifyDate;

    public final StringPath voucherCode = createString("voucherCode");

    public final DateTimePath<java.util.Date> voucherExdate = createDateTime("voucherExdate", java.util.Date.class);

    public final DateTimePath<java.util.Date> voucherIssueDate = createDateTime("voucherIssueDate", java.util.Date.class);

    public QVoucherC(String variable) {
        super(VoucherC.class, forVariable(variable));
    }

    public QVoucherC(Path<? extends VoucherC> path) {
        super(path.getType(), path.getMetadata());
    }

    public QVoucherC(PathMetadata metadata) {
        super(VoucherC.class, metadata);
    }

}

