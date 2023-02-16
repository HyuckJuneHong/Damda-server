# Damda-server
> MSA 기반의 사이드 프로젝트입니다.
- 프로젝트 기간 : 2023.02.01 - continue

### 개발환경 / 사용기술
- 개발환경 : Java 11, Spring Boot 2.6.7, Gradle(Build Tool), Git, MySQL
- 사용 기술
  - JPA, Swagger, Handler Exception, Spring Security, JWT
  - Eureka, API Gateway, Feign Client, ErrorDecoder, CircuitBreaker
  - Zipkin, Spring Cloud Sleuth
  - Spring Cloud Bus, Spring Actuator, RabbitMQ, Docker

## 예상 서비스
<img width="557" alt="image" src="https://user-images.githubusercontent.com/31675711/216048477-05f0d93e-ee70-4fce-b019-a3e46bc3719b.png">

## 예상 애플리케이션
<img width="1077" alt="image" src="https://user-images.githubusercontent.com/31675711/216955004-049acb53-8700-4e1a-b386-ddd313d9934d.png">

## 예상 설정 정보
<img width="577" alt="image" src="https://user-images.githubusercontent.com/31675711/216890973-0fb56162-d975-44cf-a0d5-b7c105a1a27d.png">

[comment]: <> (## Kafka Connector + Database )

[comment]: <> (<img width="777" alt="image" src="https://user-images.githubusercontent.com/31675711/217210434-f1c432fc-5c5f-447a-943e-22a550739bf6.png">)

# 프로젝트 기록
<ol>
    <h3>각 서비스 공통 기술</h3>
    <li>각 서비스 Eureka Server 및 API Gateway 등록 및 도커 이미지화
        <ul>
            <li>랜덤 포트 부여</li>
            <li>Swagger를 활용한 API 문서 자동화</li>
            <li>Handler Exception 처리</li>
            <li>모든 서비스 컨테이너화</li>
        </ul>
    </li>
    <li>Communication
        <ul>
            <li>Synchronous(동기) HTTP 통신
                <ul>
                    <li>하나의 클라이언트 요청에 대해 끝나기 전까지 다른 작업을 처리하지 못한다.</li>
                    <li>RestTemplate, FeignClient의 HTTP 통신 방법으로 구현이 가능.</li>
                </ul>
            </li>
            <li>Asynchronous(비동기) 통신 AMQP
                <ul>
                    <li>AMQP 프로토콜을 이용해 각 마이크로서비스 간에 비동기 방식으로 통신</li>
                    <li>변경된 사항을 전달해주고 Service는 더 이상 관여를 하지 않는 방식</li>
                </ul>
            </li>
            <li>필자의 선택 : Feign Web Service Client(Feign Client->HTTP Client)
                <ul>
                    <li>REST Call 방식을 사용하기 위해 추상화되어 있는 인터페이스를 제공</li>
                    <li>Spring Cloud Netflix에 포함되는 라이브러리</li>
                    <li>RestTemplate보다 훨씬 직관적이고 간단</li>
                    <li>Load Balanced 지원</li>
                </ul>
            </li>
            <li>Feign Client
                <ul>
                    <li>Feign Logger를 활용한 Feign Client 로그 추적</li>
                    <li>Error Decoder를 활용한 Feign Exception 처리</li>
                </ul>
            </li>
            <li>Microservice 통신 연쇄 오류
                <ul>
                    <li>각 서비스끼리 통신을 하다보니, 통신 연쇄 오류를 발견.</li>
                    <li>때문에, 통신할 서비스에서 에러 발생 시, 에러를 대신할 메소드를 만들어서 해결하는 것이 좋겠다고 판단.</li>
                    <li>즉, 장애 발생 시, CircuitBreaker를 이용해서 다른 기능으로 대체 수행. = 장애 회피</li>
                </ul>
            </li>
        </ul> 
    </li>
    <li>분산 추적
        <ul>
            <li> Zipkin
                <ul>
                    <li>분산 추적을 하기위해, Twitter에서 사용하는 Zipkin이란 오픈소스 활용</li>
                    <li>분산 환경의 Timing Data 수집, 추적 시스템으로 시스템 병목 현상 파악</li>
                    <li>Collector, Query Service, Databasem WebUI로 구성</li>
                    <li> Span
                        <ul>
                            <li>하나의 요청에 사용되는 작업 단위</li>
                            <li>64 bit unique ID</li>
                        </ul>
                    </li>
                    <li> Trace
                        <ul>
                            <li>Tree 구조로 이뤄진 Span Set</li>
                            <li>하나의 요청에 대한 같은 Trace ID 발급</li>
                        </ul>
                    </li>
                    <li>
                        <img width="507" alt="image" src="https://user-images.githubusercontent.com/31675711/218035718-6696ce15-b1a7-49da-874a-5eff674fd8a6.png">
                    </li>
                </ul>
            </li>
            <li>Spring Cloud Sleuth + Zipkin
                <ul>
                    <li>Zipkin과 연동해서 현재 갖고 있는 Log 파일 등의 데이터를 전달할 목적</li>
                    <li>요청 값에 따라 Trace ID, Span ID를 부여한다.</li>
                    <li>즉, Trace와 Span ID 들을 로그에 추가해서 지속적으로 누적된 데이터를 시각화 해보자.</li>
                    <li>
                        <img width="507" alt="image" src="https://user-images.githubusercontent.com/31675711/218038060-2cf36276-e90d-4148-85a0-fba6fd2c5ded.png">
                    </li>
                </ul>
            </li>
        </ul>
    </li>
</ol>

[comment]: <> (    <li>Apache Kafka )

[comment]: <> (        <ul> )

[comment]: <> (            <li>상태 및 구성에 대한 변경사항을 각 마이크로서비스에게 효율적으로 전달하기 위해, 각 마이크로서비스를 Kafka가 아닌, rabbitMQ와 연결한 이유와 동일하게, 해당 프로젝트에서 Kafka를 사용하는 것은 오버엔지니어링이다.</li>)

[comment]: <> (            <li>그리고 필자는 아직 대용량 트레픽을 경험 해본 적도 없다. 하지만, 당장 경험이 없다고 해서, Kafka를 적용해보지 않으면, 대용량 트레픽을 감당해야할 때, 많은 어려움을 겪을 거라 생각하고 직접 적용해보기로 했다.</li>)

[comment]: <> (            <li>필자는 Database와 마이크로서비스 사이에 Message Queuing Server&#40;Apache Kafka&#41;를 이용하여 중간 매개체를 둘 것이다.</li>)

[comment]: <> (            <li>사용 목적 : 프로그래밍 작업은 최소화하면서, Database 개수와 상관없이 마이크로서비스에 직접 Database에 대한 커넥션과 처리작업을 하지 않고, 관련 작업을 Kafka에 일임함으로 써, 보다 비즈니스 도메인에 관련된 처리 작업을 해보자.</li>)

[comment]: <> (            <li>결론 : 성능 향상 관점에서 보자.</li>)

[comment]: <> (        </ul>)

[comment]: <> (    </li>)

<ol>
    <h3><a href="https://github.com/HyuckJuneHong/damda-eureka-server">Eureka</a></h3>
    <li>Eureka Server 구축</li>
</ol>

<ol>
    <h3><a href="https://github.com/HyuckJuneHong/damda-gateway-server">API Gateway</a></h3>
    <li>
        Gateway Server 구축
        <ul>
            <li>Spring Cloud API Gateway는 비동기 방식으로 서비스가 실행</li>
            <li>각 서비스 Load Balancer 적용</li>
        </ul>
    </li>
    <li>
        Custom, Global, Logging Filter Test -> Mono 사용.
        <ul>
            <li>의문점: Flux도 0~1개의 데이터 전달이 가능한데, Mono 라는 타입이 필요할까?</li>
            <li>해답 : 데이터 설계 시, 결과가 없거나 하나의 결과값만 받는 것이 명백한 경우, List를 사용하지 않는 것처럼, 불필요하게 Flux를 사용하지 않고 Mono를 사용하는 것.</li>
        </ul>
    </li>
    <li>
        By 토비: WebFlux를 아래와 같은 용도로 사용하는 것을 추천한다.
        <ul>
            <li>비동기 : Non-Blocking Reactive 개발에 사용.</li>
            <li>효율적으로 동작하는 고성능 Web Application 개발.</li>
            <li>Service 간 호출이 많은 MSA에 적합</li>
        </ul>
    </li>
    <li>
        Gateway Authorization Header filter 구현
        <ul>
            <li>모든 요청은 서비스 최전방에 위치하는,API Gateway를 통해서만 서비스에 접근 가능하기 때문에, Gateway Filter에서 인가처리하도록 구현</li>
            <li>즉,모든 요청 Authorization Header에 Access Token을 담아 요청을 보내면 서버에서 일련의 인가 과정을 거쳐 Resource Server에 요청을 라우팅하도록 했다.</li>
        </ul>
    </li>
</ol>

<ol>
    <h3><a href="https://github.com/HyuckJuneHong/damda-user-server">User</a></h3>
    <li>User Server 구축</li>
    <li>
        JWT 및 Spring Security 활용
        <ul>
            <li>Password 인코딩 후 디비에 저장</li>
            <li>Login 성공 시, JWT Token 발급</li>
        </ul>
    </li>
</ol>

<ol>
    <h3><a href="https://github.com/HyuckJuneHong/damda-product-server">Product</a></h3>
    <li>Product Server 구축</li>
</ol>

<ol>
    <h3><a href="https://github.com/HyuckJuneHong/damda-order-server">Order</a></h3>
    <li>Order Server 구축</li>
</ol>

[comment]: <> (<ol>)

[comment]: <> (    <h3><a href="https://github.com/HyuckJuneHong/Damda-server/tree/main/damda-core">Core</a></h3>)

[comment]: <> (    <li>코드 공통화)

[comment]: <> (        <ul>)

[comment]: <> (            <li>Enum 속성 공통화</li>)

[comment]: <> (            <li>Exception 처리 공통화</li>)

[comment]: <> (            <li>Error Model 공통화</li>)

[comment]: <> (        </ul>)

[comment]: <> (    </li>)

[comment]: <> (</ol>)

<ol>
    <h3><a href="https://github.com/HyuckJuneHong/damda-config-server">Spring Cloud Config Server</a></h3>
    <li>분산 시스템에서 서버 클라이언트 구성에 필요한 설정 정보를 외부 시스템에서 관리를 위함.</li>
        <ul>
            <li>하나의 중앙화된 저장소에서 구성요소 관리</li>
            <li>각 서비스 재빌드를 하지 않고 바로 적용</li>
            <li>애플리케이션 배포 파이프라인을 통해 환경에 맞는 구성 정보 사용</li>
        </ul>
    </li>
    <li> 외부 설정 정보 활용 및 Changed Configuration Values
        <ul>
            <li>Spring Cloud Starter Config 정보 가져옴.</li>
            <li>Actuator 사용하여, 효율적으로 application 상태 및 모니터링</li>
            <li>Spring Cloud Bus 사용하여, 분산 시스템의 노드(=마이크로서비스)를 경량 메시지 브로커(=RabbitMQ)와 연결하여 상태, 구성에 대한 변경 사항 연결 노드에게 전달</li>
            <li>SSH Key 생성하여 private git repository 접근</li>
        </ul>
    </li>
    <li>AMQP(Advanced Message Queuing Protocol) 사용
        <ul>
            <li>RabbitMQ 활용</li>
            <li>왜 Kafka가 아닌, RabbitMQ를 선택했나?
                <ul>
                    <li>최고 처리량 Kafka : 605MB/s </li>
                    <li>최고 처리량 RabbitMQ : 38MB/s </li>
                    <li>Kafka는 대용량 데이터를 빠른 시간내에 처리하고자 할 때 적합.</li>
                    <li>하지만 해당 프로젝트는 대용량 데이터라고 하기엔 부족하기 때문에, </li>
                    <li>적은 데이터를 안전하게 전달하는 것을 보장해주는 RabbitMQ를 선택하게 되었다.</li>
                    <li>하지만, RabbitMQ는 이벤트 메시지가 성공적으로 잘 전달되면, 메시지 큐에서 삭제되기 때문에, 소비자와 브로커 결합력이 높아진다.</li>
                    <li>따라서 트래픽이 증가하면 수평적으로 확장하기 어려울 것 같다.</li>
                    <li>반면에, Kafka는 이벤트 스트림에서 계속 토픽을 유지하기 때문에, RabbitMQ에 비해 유연하고 느슨한 결합을 가진다.</li>
                    <li>따라서, 추후에 대규모 트래픽이 예상이 되고, 확장이 예상된다면 Kafka를 공부하고 적용해보는 시간도 가져보자.</li>
                    <li><a href="https://hongdosan.tistory.com/entry/RabbitMQ-AMQP%EC%99%80-RabbitMQ-%EB%9E%80">RabbitMQ란? 그리고 kafka와 차이점은?</a></li>
                </ul>
            </li>
        </ul>
    </li>
</ol>
