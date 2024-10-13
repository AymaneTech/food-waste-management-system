package net.foodeals.contentManagement.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import net.foodeals.common.models.AbstractEntity;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(name = "article")

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Article extends AbstractEntity<UUID> {

    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    private String title;

    @Column(unique = true)
    private String slug;

    private String content;

    @JoinColumn(name = "thumbnail_imageId")
    private String thumbnailImage;

    @ManyToOne
    @JoinColumn(name = "article_category_id")
    @JsonIgnore
    private ArticleCategory category;
}