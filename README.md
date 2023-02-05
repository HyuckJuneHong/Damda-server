# Damda-server
> MSA 기반의 사이드 프로젝트입니다.

- 프로젝트 기간 : 2023.02.01 - continue

# 프로젝트 기록
<ol>
    <h3>Eureka</h3>
    <h3>API Gateway</h3>
        <li>
            <ul>
                Gateway Server 구축
                <li>Spring Cloud API Gateway는 비동기 방식으로 서비스가 실행</li>
            </ul>
            <ul>
                Custom, Global, Loggin Filter Test -> Mono 사용.
                <li>의문점: Flux도 0~1개의 데이터 전달이 가능한데, Mono 라는 타입이 필요할까?</li>
                <li>해답 : 데이터 설계 시, 결과가 없거나 하나의 결과값만 받는 것이 명백한 경우, List를 사용하지 않는 것처럼, 불필요하게 Flux를 사용하지 않고 Mono를 사용하는 것.</li>
                By 토비: WebFlux를 아래와 같은 용도로 사용하는 것을 추천한다.
                <li>비동기 : Non-Blocking Reactive 개발에 사용.</li>
                <li>효율적으로 동작하는 고성능 Web Application 개발.</li>
                <li>Service 간 호출이 많은 MSA에 적합</li>
            </ul>
            <ul>
                Gateway Authorization Header filter 구현
            </ul>
    <h3>User</h3>
    <h3>Product</h3>
    <h3>Order</h3>
    <h3>Core</h3>
</ol>

# 전체 어플리케이션 구성
## 예상 서비스 구성
<img width="554" alt="image" src="https://user-images.githubusercontent.com/31675711/216048477-05f0d93e-ee70-4fce-b019-a3e46bc3719b.png">


