package io.taesu.urlshortner.domain

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository


/**
 * Created by itaesu on 2024/02/14.
 *
 * @author Lee Tae Su
 * @version url-shortner
 * @since url-shortner
 */
@Table("shorten_url")
class ShortenUrlEntity(
    @Id
    val urlKey: Long = 0L,
    @Column("original_url")
    val originalUrl: String,
    @Column("hash")
    val hash: String,
)


interface ShortenUrlEntityRepository: CrudRepository<ShortenUrlEntity, Long>
