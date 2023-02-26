package com.rahul.articleBlogApi.comments;

import com.rahul.articleBlogApi.articles.ArticleEntity;
import com.rahul.articleBlogApi.commons.BaseEntity;
import com.rahul.articleBlogApi.users.UserEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity(name = "comments")
public class CommentEntity extends BaseEntity {

    @Column(nullable = false, length = 100)
    String title;

    @Column(length = 1000)
    String body;

    @ManyToOne
    UserEntity author;

    @ManyToOne
    ArticleEntity article;

}
