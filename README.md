# Damda-server
> MSA 기반의 사이드 프로젝트입니다.

- 프로젝트 기간 : 2023.02.01 - continue
 
## 예상 서비스
<img width="554" alt="image" src="https://user-images.githubusercontent.com/31675711/216048477-05f0d93e-ee70-4fce-b019-a3e46bc3719b.png">

## 예상 애플리케이션
<img width="1070" alt="image" src="https://user-images.githubusercontent.com/31675711/216807120-546d169c-3de2-4d2c-a671-71be715739b6.png">

## 예상 설정 정보
<img width="577" alt="image" src="https://user-images.githubusercontent.com/31675711/216890973-0fb56162-d975-44cf-a0d5-b7c105a1a27d.png">

# 프로젝트 기록
<ol>
    <h3><a href="https://github.com/HyuckJuneHong/damda-eureka-server">Eureka</a></h3>
        <li>
            Eureka Server 구축
        </li>
 </ol>
 <ol>
    <h3><a href="https://github.com/HyuckJuneHong/damda-gateway-server">API Gateway</a></h3>
        <li>
            Gateway Server 구축
            <ul>
                <li>Spring Cloud API Gateway는 비동기 방식으로 서비스가 실행</li>
                <li>Eureka Server에 등록</li>
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
                <li></li>
            </ul>
        </li>
        <li> 외부 설정 정보 활용 및 Changed Configuration Values
            <ul>
                <li>Spring Cloud Starter Config 정보 가져옴.</li>
                <li>Actuator와 Spring Cloud Bus를 사용해서, 효율적으로 application 상태 및 모니터링</li>
            </ul>
        </li>
 </ol>
 <ol>
    <h3><a href="https://github.com/HyuckJuneHong/damda-user-server">User</a></h3>
        <li>
            User Server 구축
            <ul>
                <li>Eureka server 등록</li>
                <li>API Gateway 등록</li>
                <li>랜덤 포트 부여</li>
                <li>Swagger를 활용한 API 자동 문서화</li>
            </ul>
        </li>
        <li>
            JWT 및 Spring Security 활용
            <ul>
                <li>Password 인코딩 후 디비에 저장</li>
                <li>Login 성공 시, JWT Token 발급</li>
            </ul>
        </li>
        <li>
            외부 설정 정보 활용 및 Changed Configuration Values
            <ul>
                <li>Spring Cloud Starter Config 정보 가져옴.</li>
                <li>Actuator와 Spring Cloud Bus를 사용해서, 효율적으로 application 상태 및 모니터링</li>
            </ul>
        </li>
</ol>
 <ol>
    <h3><a href="https://github.com/HyuckJuneHong/damda-product-server">Product</a></h3>
        <li>Product Server 구축
            <ul>
                <li>Eureka server 등록</li>
                <li>API Gateway 등록</li>
                <li>랜덤 포트 부여</li>
                <li>Swagger를 활용한 API 자동 문서화</li>
            </ul>
        </li>
 </ol>
 <ol>
    <h3><a href="https://github.com/HyuckJuneHong/damda-order-server">Order</a></h3>
        <li>Order Server 구축
            <ul>
                <li>Eureka server 등록</li>
                <li>API Gateway 등록</li>
                <li>랜덤 포트 부여</li>
                <li>Swagger를 활용한 API 문서 자동화 </li>
            </ul>
        </li>
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
    <li>SSH Key 생성하여 private git repository에 접근</li>
</ol>
