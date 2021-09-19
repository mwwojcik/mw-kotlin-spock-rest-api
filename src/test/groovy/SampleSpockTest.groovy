import mw.api.model.RemoteCodeRepository
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Shared
import spock.lang.Specification

class SampleSpockTest extends IntegrationTest{

    @Autowired
    private var RemoteCodeRepository remoteCodeRepository

    @Shared message = 'Hello world!'

    def "The world can say hello using when and then"() {
        when:
        def newMessage = message
        then:
        newMessage == 'Hello world!'
    }

    def "The world can say hello using expect"(){
        expect:
        message == 'Hello world!'
    }
}