package com.example.lolstatistic.data_base

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "match")
class MatchModelForDataBase {
    var gameMode: String? = null
    @PrimaryKey
    var gameId: String? = null
    var participant0:String?=null
    var participant1:String?=null
    var participant2:String?=null
    var participant3:String?=null
    var participant4:String?=null
    var participant5:String?=null
    var participant6:String?=null
    var participant7:String?=null
    var participant8:String?=null
    var participant9:String?=null
    var win0: Boolean = true
    var kills0: Int? = null
    var assists0: Int? = null
    var deaths0: Int? = null
    var championName0: String? = null
    var lane0: String? = null
    var dragonKills0: String? = null
    var doubleKills0: String? = null
    var puuid0: String? = null
    var win1: Boolean = true
    var kills1: Int? = null
    var assists1: Int? = null
    var deaths1: Int? = null
    var championName1: String? = null
    var lane1: String? = null
    var dragonKills1: String? = null
    var doubleKills1: String? = null
    var puuid1: String? = null
    var win2: Boolean = true
    var kills2: Int? = null
    var assists2: Int? = null
    var deaths2: Int? = null
    var championName2: String? = null
    var lane2: String? = null
    var dragonKills2: String? = null
    var doubleKills2: String? = null
    var puuid2: String? = null
    var win3: Boolean = true
    var kills3: Int? = null
    var assists3: Int? = null
    var deaths3: Int? = null
    var championName3: String? = null
    var lane3: String? = null
    var dragonKills3: String? = null
    var doubleKills3: String? = null
    var puuid3: String? = null
    var win4: Boolean = true
    var kills4: Int? = null
    var assists4: Int? = null
    var deaths4: Int? = null
    var championName4: String? = null
    var lane4: String? = null
    var dragonKills4: String? = null
    var doubleKills4: String? = null
    var puuid4: String? = null
    var win5: Boolean = true
    var kills5: Int? = null
    var assists5: Int? = null
    var deaths5: Int? = null
    var championName5: String? = null
    var lane5: String? = null
    var dragonKills5: String? = null
    var doubleKills5: String? = null
    var puuid5: String? = null
    var win6: Boolean = true
    var kills6: Int? = null
    var assists6: Int? = null
    var deaths6: Int? = null
    var championName6: String? = null
    var lane6: String? = null
    var dragonKills6: String? = null
    var doubleKills6: String? = null
    var puuid6: String? = null
    var win7: Boolean = true
    var kills7:Int? = null
    var assists7: Int? = null
    var deaths7: Int? = null
    var championName7: String? = null
    var lane7: String? = null
    var dragonKills7: String? = null
    var doubleKills7: String? = null
    var puuid7: String? = null
    var win8: Boolean = true
    var kills8: Int? = null
    var assists8: Int? = null
    var deaths8: Int? = null
    var championName8: String? = null
    var lane8: String? = null
    var dragonKills8: String? = null
    var doubleKills8: String? = null
    var puuid8: String? = null
    var win9: Boolean = true
    var kills9: Int? = null
    var assists9: Int? = null
    var deaths9: Int? = null
    var championName9: String? = null
    var lane9: String? = null
    var dragonKills9: String? = null
    var doubleKills9: String? = null
    var puuid9: String? = null
}