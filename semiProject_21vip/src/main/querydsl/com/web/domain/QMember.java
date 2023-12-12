package com.web.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = -1624053027L;

    public static final QMember member = new QMember("member1");

    public final StringPath memberBirth = createString("memberBirth");

    public final StringPath memberDept = createString("memberDept");

    public final StringPath memberEmail = createString("memberEmail");

    public final StringPath memberFile = createString("memberFile");

    public final StringPath memberName = createString("memberName");

    public final EnumPath<Role> memberRole = createEnum("memberRole", Role.class);

    public final StringPath password = createString("password");

    public final StringPath username = createString("username");

    public QMember(String variable) {
        super(Member.class, forVariable(variable));
    }

    public QMember(Path<? extends Member> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMember(PathMetadata metadata) {
        super(Member.class, metadata);
    }

}

