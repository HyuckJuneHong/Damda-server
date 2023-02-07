# Damda-server
> MSA 기반의 사이드 프로젝트입니다.
- 프로젝트 기간 : 2023.02.01 - continue

### 개발환경
- Java 11, Spring Boot 2.6.7, Gradle(Build Tool)
 
## 예상 서비스
<img width="557" alt="image" src="https://user-images.githubusercontent.com/31675711/216048477-05f0d93e-ee70-4fce-b019-a3e46bc3719b.png">

## 예상 애플리케이션
<img width="1077" alt="image" src="https://user-images.githubusercontent.com/31675711/216955004-049acb53-8700-4e1a-b386-ddd313d9934d.png">

## 예상 설정 정보
<img width="577" alt="image" src="https://user-images.githubusercontent.com/31675711/216890973-0fb56162-d975-44cf-a0d5-b7c105a1a27d.png">

# 프로젝트 기록
<ol>
    <h3>각 서비스 공통 작업</h3>
    <li>각 서비스 Eureka Server 및 API Gateway 등록
        <ul>
            <li>랜덤 포트 부여</li>
            <li>Swagger를 활용한 API 문서 자동화</li>
        </ul>
    </li>
    <li>Communication Types
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
                    <li>Feign Client 로그 추적</li>
                    <li>Feign Exception 처리</li>
                </ul>
            </li>
        </ul>
    </li>
</ol>
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
            <li>각 서비스 Load Balancer 적용 -></li>
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
 <ol>
    <h3><a href="https://github.com/HyuckJuneHong/Damda-server/tree/main/damda-core">Core</a></h3>
    <li>코드 공통화
        <ul>
            <li>Enum 속성 공통화</li>
            <li>Exception 처리 공통화</li>
            <li>Error Model 공통화</li>
        </ul>
    </li>
</ol>
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
            <li>Spring Cloud Bus 사용하여, 분산 시스템의 노드를 경량 메시지 브로커와 연결하여 상태, 구성에 대한 변경 사항 연결 노드에게 전달</li>
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
                </ul>
            </li>
        </ul>
    </li>
</ol>
