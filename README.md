## gridge-test
그릿지 테스트 웹 챌린지 - 혁규

관리자 전용 계정

아이디 : gyuhyeok

비밀번호 : string
## 🛠️ 개발 환경
|||
|:-:|:-:|
|통합 개발 환경|IntelliJ|
|Spring 버전|3.2.3|
|데이터베이스|AWS RDS(MySQL), Redis|
|배포|AWS Elastic beanstalk, ElastiCache(redis)|
|Project 빌드 관리 도구|Gradle|
|Java version|java 17|
|패키지 구조|도메인 패키지 구조|
|API 테스트|PostMan, Swagger(https://dev.gridgetest.shop/swagger-ui/index.html#/)|

## ☁️ERD
<img width="675" alt="erd" src="https://github.com/Gyuhyeok99/gridge-test/assets/126947828/42abb757-97a3-4952-9764-0569cdef2d42">

## ✨Structure
앞에 (*)이 붙어있는 파일(or 폴더)은 추가적인 과정 이후에 생성된다.
```text
api-server-spring-boot
  > .ebextensions-dev
    | 00-makeFiles.config
    | 01-set-timezone.config
  > .github
    > ISSUE_TEMPLATE
      | ✨feat.md
      | 🆘help.md
      | 🐛bug-report.md
      | 🚑fix.md
    > worksflows
      | develop_dev.yml // github action을 위한 파일
  > .platform
    | nginx.conf // nginx 설정
  > * build
  > gradle
  > src.main.java.com.example.demo
    > common
      > code
        > status
          | ErrorStatus.java // 에러 응답 메시지 모아놓은 곳
          | SuccessStatus.java // 성공 응답 메시지 모아놓은 곳
        | BaseCode.java
        | BaseErrorCode.java
        | ErrorReasonDTO.java
        | ReasonDTO.java
      > config
        | AppConfig.java
        | ImportConfig.java // 결제 관련 설정
        | RedisConfig.java // 레디스 관련 설정
        | RestTemplateConfig.java // HTTP get,post 요청을 날릴때 일정한 형식에 맞춰주는 template
        | Security.config.java // Spring Security 관련 설정
        | SwaggerConfig.java // Swagger 관련 설정
        | WebConfig.java // Web 관련 설정(CORS 설정 포함)
      > entity
        | BaseEntity.java // create, update, state 등 Entity에 공통적으로 정의되는 변수를 정의한 BaseEntity
      > exceptions
        > handler
          | ExceptionHandler.java
        | BaseException.java // Controller, Service에서 Response 용으로 공통적으로 사용 될 익셉션 클래스
        | ExceptionAdvice.java // ExceptionHandler를 활용하여 정의해놓은 예외처리를 통합 관리하는 클래스
      > log
        > entity
          | Log.java
        | LogRepository.java
        | Trace.java
        | TraceAspect.java //Aop를 활용하여 로그 RDBMS에 저장
      > oauth
        > kakao 
          | kakaoService.java //카카오 로그인 관련 로직
      > response
        | BaseResponse.java // Controller 에서 Response 용으로 공통적으로 사용되는 구조를 위한 모델 클래스
      > validation // Request, Reponse 시 값 검증하는 사용자 정의 어노테이션 관리
        > annotation
        > validator
      | Constant // 상수 보관 클래스
    > src
      > admin //관리자 페이지 관련 api
        > model
          > enums
            | DomainName.java
          | BoardSearchCond.java
          | GetCondBoardRes.java
          | GetCondCommentRes.java
          | GetCondImageRes.java
          | GetCondUserRes.java
          | GetLogRes.java
          | GetPaymentRes.java
          | GetReportRes.java
          | UserSearchCond.java
        | AdminController
        | AdminConverter
        | AdminQueryRepository
        | AdminService
      > auth // 인증 필요 없는 api ex) 로그인, 회원가입
        > model
          | PatchChangePasswordReq.java
          | PostCheckPhoneReq.java
          | PostCheckUsernameReq.java
          | PostFindCheckReq.java
          | PostFindPhoneReq.java
          | PostLoginReq.java
          | PostLoginRes.java
          | PostRefreshRes.java
          | PostSocialRes.java
          | PostUserReq.java
          | PostUserRes.java
        | AuthController.java
        | AuthConverter.java
        | AuthService.java
      > board // 게시글 관련
        > entity
          | Board
          | BoardImage
        > model
          | GetBoardImageRes.java
          | GetBoardRes.java
          | GetUsernameRes.java
          | PatchBoardImageReq.java
          | PatchBoardReq.java
          | PatchBoardRes.java
          | PostBoardImageReq.java
          | PostBoardReq.java
          | PostBoardRes.java
          | PostLikesRes.java
          | PostReportReq.java
          | PostReportRes.java
        | BoardController.java
        | BoardConverter.java
        | BoardImageRepository.java
        | BoardRepository.java
        | BoardService.java
      > comment // 댓글 관련
        > entity
          | Comment
        > model
          | GetCommentRes.java
          | PatchCommentReq.java
          | PatchCommentRes.java
          | PostCommentReq.java
          | PostCommentRes.java
        | CommentController.java
        | CommentConverter.java
        | CommentRepository.java
        | CommentService.java
      > mapping // mapping table 
        > entity
          > enums
            | ReportContent
          | BoardLikes
          | BoardReport
        | BoardLikesRepository.java
        | BoardReportRepository.java
      > payment // 결제 관련
        > entity
          > enums
            | PaymentStatus
          | Payment
        > model
          | PostPayReq.java
          | PostPayRes.java
        | PaymentController.java
        | PaymentConverter.java
        | PaymentRepository.java
        | PaymentService.java
      > user // user 관련
        > entity
          > enums
            | Permission.java
            | Role.java
          | User.java // User Entity
        > model
          | GetSocialOAuthRes.java 
          | GetUserRes.java    
          | PatchUserReq.java 
        | UserController.java
        | UserService.java
        | UserRepository.java
    > utils
      > jwt // jwt 관련
        | JwtAuthenticationFilter.java
        | JwtProvider.java
        | LogoutService.java // 로그아웃
      | ApplicationAuditAware.java
      | RedisProvider.java // 레디스 서비스
      | SmsUtil.java // 문자 관련
      | TermsAgreedScheduler.java // 스케줄러 관련
    | DemoApplication // SpringBootApplication 서버 시작 지점
  > resources
    | application.yml // Database 연동을 위한 설정 값 세팅 및 Port 정의 파일
    | application-dev.yml // dev 연동
    | application-local.yml // local 연동
    | logback-spring.xml // logback 설정 xml 파일
build.gradle // gradle 빌드시에 필요한 dependency 설정하는 곳
.gitignore // git 에 포함되지 않아야 하는 폴더, 파일들을 작성 해놓는 곳

```
## 환경 설정 내역
- local 실행 시 로컬 디비 접속 포트번호 9000
- dev 실행 시 dev.gridgetest.shop로 접속(배포 주소 : https://dev.gridgetest.shop/)

## 🌱 Branch
-  main : 최종
-  develop : 개발
-  feat : 기능 개발
-  refactor : 기능 수정

## ✨License
- 본 템플릿의 소유권은 소프트스퀘어드에 있습니다. 본 자료에 대한 상업적 이용 및 무단 복제, 배포 및 변경을 원칙적으로 금지하며 이를 위반할 때에는 형사처벌을 받을 수 있습니다.# Gridge-Test
