package com.web.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = 968392910L;

    public static final QUser user = new QUser("user");

    public final DateTimePath<java.util.Date> userBirth = createDateTime("userBirth", java.util.Date.class);

    public final StringPath userDept = createString("userDept");

    public final StringPath userFile = createString("userFile");

    public final StringPath userId = createString("userId");

    public final StringPath userName = createString("userName");

    public final StringPath userPw = createString("userPw");

    public final DateTimePath<java.util.Date> userRegisterDate = createDateTime("userRegisterDate", java.util.Date.class);

    public final StringPath userRegisterId = createString("userRegisterId");

    public final StringPath userRole = createString("userRole");

    public final DateTimePath<java.util.Date> userUpdateDate = createDateTime("userUpdateDate", java.util.Date.class);

    public final StringPath userUpdateId = createString("userUpdateId");

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

