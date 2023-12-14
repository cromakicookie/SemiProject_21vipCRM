package com.web.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCustomer is a Querydsl query type for Customer
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCustomer extends EntityPathBase<Customer> {

    private static final long serialVersionUID = -386213023L;

    public static final QCustomer customer = new QCustomer("customer");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final StringPath createBy = _super.createBy;

    //inherited
    public final DateTimePath<java.util.Date> createDate = _super.createDate;

    public final StringPath customerBirth = createString("customerBirth");

    public final StringPath customerGender = createString("customerGender");

    public final StringPath customerGrade = createString("customerGrade");

    public final StringPath customerHP = createString("customerHP");

    public final ListPath<CustomerMemo, QCustomerMemo> customerMemoList = this.<CustomerMemo, QCustomerMemo>createList("customerMemoList", CustomerMemo.class, QCustomerMemo.class, PathInits.DIRECT2);

    public final StringPath customerName = createString("customerName");

    public final StringPath customerNum = createString("customerNum");

    public final StringPath favoriteStore = createString("favoriteStore");

    public final ListPath<VoucherC, QVoucherC> issuedVoucherList = this.<VoucherC, QVoucherC>createList("issuedVoucherList", VoucherC.class, QVoucherC.class, PathInits.DIRECT2);

    public final ListPath<Luxury, QLuxury> luxuryList = this.<Luxury, QLuxury>createList("luxuryList", Luxury.class, QLuxury.class, PathInits.DIRECT2);

    //inherited
    public final StringPath modifyBy = _super.modifyBy;

    //inherited
    public final DateTimePath<java.util.Date> modifyDate = _super.modifyDate;

    public QCustomer(String variable) {
        super(Customer.class, forVariable(variable));
    }

    public QCustomer(Path<? extends Customer> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCustomer(PathMetadata metadata) {
        super(Customer.class, metadata);
    }

}

