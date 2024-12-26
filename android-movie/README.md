# android-movie

## [Figma](https://www.figma.com/design/dzSLq1WTd2bebZ70bBvccW/%EC%9A%B0%ED%85%8C%EC%BD%946%EA%B8%B0-Android?node-id=1-131&t=k6cQTbmCymPsD14f-0)

## 기능 요구사항
사용자는 영화 예매 시스템을 이용해 쉽고 빠르게 보고 싶은 영화를 예매할 수 있다.
- 여러 영화 목록이 보여진다.
    - 영화를 선택하면 세부 사항과 예매완료 버튼이 floating 으로 보인다
    -  세부 화면에서 '예매 완료' 버튼을 누르면 3개의 극장을 선택한다.
- 목록에서 '지금 예매' 버튼을 누르면 3개의 극장을 선택한다. 
- 극장 선택을 하면 예매가 바로 완료된다.
- 좌석과 상영일은 편의상 하나만 지정해서 넣어준다.
- 광고(우아한테크코스)부분은 누르면 'www.dreamfora.com' 으로 연결되게 한다. 

## 프로그램 요구사항
- RecyclerView + ListAdapter 를 사용한다.
    - Concat Adapter 는 사용하지 않고, ViewType 을 나눠서 구현한다.
- 버튼은 클릭효과가 나도록 한다.
- Button 을 사용하지 말고 CardView 또는 Material CardView 를 사용하여 Custom 한다.
- 상단 Toolbar 도 Android Widget 인 Toolbar 를 사용하지말고 Custom 한다.
