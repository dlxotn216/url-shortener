package io.taesu.urlshortner.shortenurl.domain

import io.taesu.urlshortner.app.exceptions.AppRuntimeException
import io.taesu.urlshortner.app.exceptions.EntityNotFoundException
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import org.springframework.data.repository.CrudRepository


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


interface ShortenUrlEntityRepository: CrudRepository<ShortenUrlEntity, Long> {
    fun findByHash(hash: String): ShortenUrlEntity?
}

fun ShortenUrlEntityRepository.findOrThrow(hash: String): ShortenUrlEntity {
    return findByHash(hash) ?: throw EntityNotFoundException("ShortenUrl[$hash] not found")
}
