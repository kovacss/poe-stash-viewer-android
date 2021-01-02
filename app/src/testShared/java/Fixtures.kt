import com.example.myapplication.repository.UrlChangingInterceptor
import com.github.tomakehurst.wiremock.client.MappingBuilder
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder
import com.github.tomakehurst.wiremock.client.WireMock
import com.github.tomakehurst.wiremock.junit.WireMockRule

object HttpFixture {
    fun getStashItemRequest(server: WireMockRule, league: String, accountName: String): MappingBuilder {
        val url = "/character-window/get-stash-items?tabs=1&league=$league&accountName=$accountName"

        UrlChangingInterceptor.setHttpUrl(server.baseUrl() + url)

        return WireMock.get(WireMock.urlEqualTo(url))
    }

    fun getStashItemResponse(body: String, status: Int = 200): ResponseDefinitionBuilder {
        return WireMock.aResponse()
            .withBody(body)
            .withStatus(status);
    }
}
