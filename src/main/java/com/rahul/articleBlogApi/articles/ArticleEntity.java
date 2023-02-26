package com.rahul.articleBlogApi.articles;

import com.rahul.articleBlogApi.commons.BaseEntity;
import com.rahul.articleBlogApi.users.UserEntity;
import org.apache.tomcat.jni.User;

import javax.persistence.*;
import java.util.List;

@Entity(name = "articles")
public class ArticleEntity extends BaseEntity {
    @Column(unique = true, nullable = false, length = 150)
    String slug;

    @Column(nullable = false, length = 200)
    String title;
    @Column(nullable = false)
    String subtitle;
    @Column(nullable = false, length = 8000)
    String body;

    @ManyToOne
    UserEntity author;

    //    @ManyToMany(mappedBy = "likedBy")  // This is one way to map a table for manytomany relation
    @ManyToMany
    @JoinTable(
            name = "articles_likes",
            joinColumns = @JoinColumn(name = "article_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    List<UserEntity> likedBy;
}
