package mw.api.external.github

import mw.api.model.RemoteCodeRepository
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate

@Configuration
@EnableConfigurationProperties(GithubProperties::class)
class GithubConfiguration {

    @Bean("githubRestTemplate")
    fun githubRestTemplate(properties: GithubProperties): RestTemplate {
        return RestTemplateBuilder().rootUri(properties.url).build()
    }

    @Bean
    fun githubClient(@Qualifier("githubRestTemplate") restTemplate: RestTemplate): GithubRemoteClient {
        return GithubRemoteClient(restTemplate)
    }

    @Bean
    fun githubRepository(githubClient: GithubRemoteClient): RemoteCodeRepository {
        return GithubRemoteCodeRepositoryProvider(githubClient)
    }
}

@ConfigurationProperties("service.external.github")
data class GithubProperties(
        var url: String? = null
)