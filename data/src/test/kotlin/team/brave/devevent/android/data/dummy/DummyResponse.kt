/*
 * DevEvent-Android © 2022 Ji Sungbin. all rights reserved.
 * DevEvent-Android license is under the MIT.
 *
 * [DummyResponse.kt] created by Ji Sungbin on 23. 1. 2. 오후 5:37
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE
 */

@file:Suppress("MaxLineLength")

package team.brave.devevent.android.data.dummy

import team.brave.devevent.android.domain.constants.EventTimeType
import team.brave.devevent.android.domain.model.Event
import team.brave.devevent.android.domain.model.Tag

object DummyResponse {
    // 436 이벤트는 tags 아이템 및 cover_image_link 가 없음
    // 284 이벤트는 organizer 가 없음
    const val RawData = """
        [
          {
            "metadata": {
              "year": 2023,
              "month": 1,
              "total": 2
            },
            "dev_event": [
              {
                "id": 433,
                "title": "프리온보딩 백엔드 챌린지 1월",
                "description": "",
                "organizer": "원티드",
                "event_link": "https://www.wanted.co.kr/events/pre_challenge_be_3",
                "cover_image_link": "https://brave-people-3.s3.ap-northeast-2.amazonaws.com/DEVEVENT/2022-12-30-22-06-1816-39373946.png",
                "display_sequence": 0,
                "event_time_type": "DATE",
                "start_day_week": "목",
                "use_start_date_time_yn": "N",
                "start_date_time": "2022-12-22 00:00",
                "end_day_week": "목",
                "use_end_date_time_yn": "N",
                "end_date_time": "2023-01-05 00:00",
                "create_date_time": "2022-12-30 22:06",
                "tags": [
                  {
                    "id": 9,
                    "tag_name": "교육",
                    "tag_color": "#5D93E4"
                  }
                ]
              },
              {
                "id": 436,
                "title": "Certificate Manager로 인증서 쉽게 관리하기",
                "description": "",
                "organizer": "네이버 클라우드 플랫폼",
                "event_link": "https://app.livestorm.co/naver-cloud/certificate-manager2",
                "display_sequence": 0,
                "event_time_type": "RECRUIT",
                "start_day_week": "목",
                "use_start_date_time_yn": "Y",
                "start_date_time": "2023-01-05 11:00",
                "end_day_week": "목",
                "use_end_date_time_yn": "Y",
                "end_date_time": "2023-01-05 12:00",
                "create_date_time": "2022-12-30 22:11",
                "tags": []
              }
            ]
          },
          {
            "metadata": {
              "year": 2023,
              "month": 2,
              "total": 1
            },
            "dev_event": [
              {
                "id": 284,
                "title": "네이버 DEVIEW 2023",
                "description": "",
                "event_link": "https://deview.kr/2023/cfs",
                "cover_image_link": "https://brave-people-3.s3.ap-northeast-2.amazonaws.com/DEVEVENT/2022-10-21-23-58-2556-6df9166d.png",
                "display_sequence": 0,
                "event_time_type": "DATE",
                "start_day_week": "월",
                "use_start_date_time_yn": "N",
                "start_date_time": "2023-02-27 00:00",
                "end_day_week": "화",
                "use_end_date_time_yn": "N",
                "end_date_time": "2023-02-28 00:00",
                "create_date_time": "2022-10-21 23:58",
                "tags": [
                  {
                    "id": 5,
                    "tag_name": "오프라인",
                    "tag_color": "#F0977B"
                  }
                ]
              }
            ]
          }
        ]
    """

    val DomainData = listOf(
        Event(
            title = "프리온보딩 백엔드 챌린지 1월",
            organizer = "원티드",
            time = "2022.12.22(목) ~ 2023.01.05(목)",
            timeType = EventTimeType.DATE,
            tags = listOf(Tag(name = "교육", hexColor = "#5D93E4")),
            eventLink = "https://www.wanted.co.kr/events/pre_challenge_be_3",
            bannerUrl = "https://brave-people-3.s3.ap-northeast-2.amazonaws.com/DEVEVENT/2022-12-30-22-06-1816-39373946.png",
        ),
        Event(
            title = "Certificate Manager로 인증서 쉽게 관리하기",
            organizer = "네이버 클라우드 플랫폼",
            time = "2023.01.05(목) 11:00 ~ 12:00",
            timeType = EventTimeType.RECRUIT,
            tags = emptyList(),
            eventLink = "https://app.livestorm.co/naver-cloud/certificate-manager2",
            bannerUrl = null,
        ),
    )
}
