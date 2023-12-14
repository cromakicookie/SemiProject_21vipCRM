package com.web.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QVoucher is a Querydsl query type for Voucher
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QVoucher extends EntityPathBase<Voucher> {

    private static final long serialVersionUID = 885274315L;

    public static final QVoucher voucher = new QVoucher("voucher");

    public final NumberPath<Integer> status = createNumber("status", Integer.class);

    public final StringPath voucherCode = createString("voucherCode");

    public final DatePath<java.util.Date> voucherEndDate = createDate("voucherEndDate", java.util.Date.class);

    public final NumberPath<Long> voucherSeq = createNumber("voucherSeq", Long.class);

    public final StringPath voucherService = createString("voucherService");

    public final StringPath voucherServiceName = createString("voucherServiceName");

    public final DatePath<java.util.Date> voucherStartDate = createDate("voucherStartDate", java.util.Date.class);

    public final StringPath voucherType = createString("voucherType");

    public QVoucher(String variable) {
        super(Voucher.class, forVariable(variable));
    }

    public QVoucher(Path<? extends Voucher> path) {
        super(path.getType(), path.getMetadata());
    }

    public QVoucher(PathMetadata metadata) {
        super(Voucher.class, metadata);
    }

}

