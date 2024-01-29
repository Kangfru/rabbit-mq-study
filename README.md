### Rabbit mq 설치
#### with homebrew
```shell
brew update
brew install rabbitmq
```
- 설치 완료 후 foreground, background 로 실행 가능
- homebrew 3.0.0 이후부터 /opt/homebrew/sbin 으로 설치되어 별도의 환경 변수 설정없이 사용가능할 것.
##### foreground
```shell
rabbitmq-server
```
- 위 서버 실행이 실패한다면 환경변수 등록 필요

##### background
```shell
brew services start rabbitmq
brew services list
```
#### GUI관리자
http://localhost:15672 를 통해 확인가능.
