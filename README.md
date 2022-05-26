# SSAFY ROOMIE
ssafy 7기 1학기 파이널 프로젝트 HAPPYHOUSE

# 🏡프로젝트 개요 
> 부동산 매물 정보와 뉴스를 확인하고 싸피인들을 위한 관심목록 관리 및 게시판 </br>

</br>
</br>

## 개발 배경
SSAFY 학생들에게 부동산과 관련된 다양한 기능을 제공하고 소통할 수 있는 서비스 제공 

## 주요 기능
- 원하는 아파트 이름을 입력하여 아파트 정보 확인 및 관련된 뉴스 데이터 제공
- 아파트의 위치와 상세 정보 뿐만 아니라 주변 편의시설(편의점, 마트, 은행, 약국) 위치 제공
- 로드뷰를 이용하여 매물의 실제 주변 확인 가능

- 회원가입(회원가입 시, 자신의 교육캠퍼스를 입력받기)
- 회원가입 시, 아이디 중복 체크, 비밀번호 일치 확인
- 회원 정보 확인, 수정, 탈퇴
- 로그인, 로그아웃, 비밀번호 찾기(아이디, 이름, 이메일 입력 시 팝업 창을 통해 비밀번호 알려주기)

- 관심목록 매물 메뉴를 통해 관심매물들은 따로 확인 가능
- 선택한 매물과 자신의 교육캠퍼스까지의 거리 및 대중교통 정보 확인

- 전체/자유/방 구하기/룸메이트 구하기로 카테고리를 나누어 게시판 글 관리
- 게시글 댓글 기능(비밀댓글)

## API
[**카카오 맵**]
- 지도 마커
- 주변 상권 확인

[**공공데이터포털**]
- 국토교통부 아파트/ 연립다세대 실거래가 자료

[**행정표준코드관리시스템**]
- 법정동 코드

[**OBSAY**]
- 대중교통 정보

## 팀원 및 역할분담
|[]()|[lye2i](https://github.com/lye2i)|
![개인파트](https://user-images.githubusercontent.com/65855978/170497377-c4a6d5d6-31c1-49e6-94ac-3964d8ad70fa.png)


# YouTube
>  ✨[demo video](https://www.youtube.com/watch?v=BEAs4nZEI5M)<br/>

## 주요 기능
- **백엔드 연결**
    - 지하도상가 점포 데이터베이스 구축
    - `Firebase`를 이용해 웹서버 구축

- **메인 페이지**
    - 지도보기
    - 쇼핑하기
    - 로그인, 회원가입

- **지하도상가 지도**
    - 매장 검색
         - 위치정보
         - 매장정보
    - 카테고리별 매장 분류
         - 패션의류, 음식점, 쇼핑미용, 기타매장, 디지털가전, 공방, 편의시설 등으로 분류
    - 채팅을 통한 매장 Q&A
         - 지도 내에서 점포 클릭 시, 페이지 전환

- **온라인 쇼핑**
    - 상품목록
    - 상점목록
         - 상점 검색
    - 채팅목록
    - 마이페이지
         - 개인정보 수정
    - 채팅을 통한 상품 구매
         - 연관상품 추천


# 🛍️Screen 구성
![layout](https://user-images.githubusercontent.com/69866091/152942764-c2f29cde-7a72-4605-9eaa-5a6848459a3c.png)
![final](https://user-images.githubusercontent.com/69866091/152943277-617b2556-ec44-4d89-a432-08886c4bc3db.jpg)


# 🛍️결과 화면

### ✨로그인
![1 로그인](https://user-images.githubusercontent.com/69866091/152654409-4aedfbb5-07e2-4bf4-9143-7586896d0963.gif)

### ✨마이페이지
![2 마이페이지](https://user-images.githubusercontent.com/69866091/152655343-91dea02a-0a44-481d-af00-72fa8128de96.png)

### ✨매장 검색
![3 매장 검색](https://user-images.githubusercontent.com/69866091/152654425-91a9c402-1c5a-4c8a-bbf2-948e991a6084.gif)

### ✨매장 정보
![4 매장 정보](https://user-images.githubusercontent.com/69866091/152654430-b8d07f84-ad0d-4584-9051-afa3725e9270.gif)

### ✨매장지도
![5 매장지도](https://user-images.githubusercontent.com/69866091/152654433-36fb29a2-1a1e-46f3-8388-57433669ce40.gif)

### ✨카테고리별 매장 분류
![6 카테고리별 매장 분류](https://user-images.githubusercontent.com/69866091/152654435-97a29f32-a388-444a-80c6-1cda50003286.gif)

### ✨채팅을 통한 매장 Q&A
![7 채팅을 통한 매장 Q A](https://user-images.githubusercontent.com/69866091/152654442-7e937729-0030-45e7-9324-b2edf196306e.gif)

### ✨상품목록
![8 상품목록](https://user-images.githubusercontent.com/69866091/152654445-e244b2ba-ee5b-4f40-bf33-a4f10265e442.gif)

### ✨상점목록
![9 상점목록](https://user-images.githubusercontent.com/69866091/152654448-5a5ba54b-e87f-4f54-9835-804b2d5322c9.gif)

### ✨상점 검색
![10 상점 검색](https://user-images.githubusercontent.com/69866091/152654458-19482c65-379a-49de-a685-4262ae1f73c6.gif)

### ✨연관상품 추천
![11 연관상품 추천](https://user-images.githubusercontent.com/69866091/152654463-73ee27a5-af2d-45e8-8247-66e3f9a5f4a9.gif)

### ✨채팅을 통한 상품 구매
![12 채팅을 통한 상품 구매](https://user-images.githubusercontent.com/69866091/152654467-7000a4a5-9534-4dc9-83b6-7632701ec3cb.gif)

### ✨채팅목록
![13 채팅목록](https://user-images.githubusercontent.com/69866091/152654481-3ada9097-7951-4577-ad53-abca2b07d26f.gif)

