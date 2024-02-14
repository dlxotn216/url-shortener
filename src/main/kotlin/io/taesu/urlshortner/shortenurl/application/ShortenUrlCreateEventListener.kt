package io.taesu.urlshortner.shortenurl.application

import io.taesu.urlshortner.shortenurl.domain.ShortenUrlCreatedEvent
import io.taesu.urlshortner.shortenurl.infra.ShortenUrlCacheRepository
import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionPhase
import org.springframework.transaction.event.TransactionalEventListener
import java.time.Duration

/**
 * Created by itaesu on 2024/02/14.
 *
 * @author Lee Tae Su
 * @version url-shortner
 * @since url-shortner
 */
@Component
class ShortenUrlCreateEventListener(
    private val shortenUrlCacheSaveService: ShortenUrlCacheSaveService,
) {
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    fun handle(event: ShortenUrlCreatedEvent) {
        // 생성 이벤트 내에서 미리 캐싱을 적용하도록 처리
        // 발급 된 단축 URL은 근 시일 내에 참조 될 가능성이 높다고 판단하여 선 캐싱을 적용.
        // 단축 URL은 발급 후 7일 이후엔 잘 접근되지 않을 가능성이 높다고 판단하여 7일로 TTL을 설정
        shortenUrlCacheSaveService.save(event.hash, event.originalUrl)
    }
}
