# kotlin-racingcar

## 기능 요구 사항
### 1
- 주어진 횟수 동안 n대의 자동차는 전진 또는 멈출 수 있다.
- 사용자는 몇 대의 자동차로 몇 번의 이동을 할 것인지를 입력할 수 있어야 한다.
- 전진하는 조건은 0에서 9 사이에서 무작위 값을 구한 후 무작위 값이 4 이상일 경우이다.
- 자동차의 상태를 화면에 출력한다. 어느 시점에 출력할 것인지에 대한 제약은 없다.
### 2
- 각 자동차에 이름을 부여할 수 있다. 자동차 이름은 5자를 초과할 수 없다.
- 전진하는 자동차를 출력할 때 자동차 이름을 같이 출력한다.
- 자동차 이름은 쉼표(,)를 기준으로 구분한다.
- 자동차 경주 게임을 완료한 후 누가 우승했는지를 알려준다. 우승자는 한 명 이상일 수 있다.

## 프로그래밍 요구 사항

- 핵심 로직을 구현하는 코드와 UI를 담당하는 로직을 구분한다.
- UI 로직을 InputView, ResultView와 같은 클래스를 추가해 분리한다.

- indent(인덴트, 들여쓰기) depth를 2를 넘지 않도록 구현한다. 1까지만 허용한다.
- 예를 들어 while문 안에 if문이 있으면 들여쓰기는 2이다.
    - 힌트: indent(인덴트, 들여쓰기) depth를 줄이는 좋은 방법은 함수를 분리하면 된다.
- 함수의 길이가 15라인을 넘어가지 않도록 구현한다.
- 함수가 한 가지 일만 잘 하도록 구현한다.

- 기능을 구현하기 전에 README.md 파일에 구현할 기능 목록을 정리해 추가한다.
- git의 commit 단위는 앞 단계에서 README.md 파일에 정리한 기능 목록 단위로 추가한다.

- 사용자가 입력한 이름의 숫자 만큼 자동차 대수를 생성한다.
- 자동차는 자동차 이름과 위치 정보를 가지는 Car 객체를 추가해 구현한다.


## 
![image](https://github.com/user-attachments/assets/a6bc4da2-557b-46da-905d-4d080cf1e9a9)


## Reference
[객체지향 생활체조 9원칙](https://tech.trenbe.com/2022/01/14/%EA%B0%9D%EC%B2%B4%EC%A7%80%ED%96%A5-%EC%83%9D%ED%99%9C%EC%B2%B4%EC%A1%B0-%EC%9B%90%EC%B9%99.html)
```
규칙 1: 한 메서드에 오직 한 단계의 들여쓰기(indent)만 한다.
규칙 2: else 예약어를 쓰지 않는다.
규칙 3: 모든 원시값과 문자열을 포장한다.
규칙 4: 한 줄에 점을 하나만 찍는다.
규칙 5: 줄여쓰지 않는다(축약 금지).
규칙 6: 모든 엔티티를 작게 유지한다.
규칙 7: 3개 이상의 인스턴스 변수를 가진 클래스를 쓰지 않는다.
규칙 8: 일급 콜렉션을 쓴다.
규칙 9: 게터/세터/프로퍼티를 쓰지 않는다
```
[범위 지정함수(apply, with, let, also, run)](https://medium.com/@limgyumin/%EC%BD%94%ED%8B%80%EB%A6%B0-%EC%9D%98-apply-with-let-also-run-%EC%9D%80-%EC%96%B8%EC%A0%9C-%EC%82%AC%EC%9A%A9%ED%95%98%EB%8A%94%EA%B0%80-4a517292df29)