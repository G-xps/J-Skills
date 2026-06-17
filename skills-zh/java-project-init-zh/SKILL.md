---
name: java-project-init-zh
description: java-project-init 鐨勪腑鏂囩増鏈紱鐢ㄤ簬灏嗙┖鎴栨帴杩戠┖鐨?Java 浠撳簱鍒濆鍖栦负涓ユ牸鐨?Spring Boot Maven 澶氭ā鍧楅」鐩紝閲囩敤 MyBatis-Plus銆丳ostgreSQL銆丗lyway 鍜屽崟 xxx-web 鍚姩妯″潡锛涗笉鐢ㄤ簬鏃ュ父鍔熻兘寮€鍙戙€侀鏍兼暣鐞嗐€乥ugfix 鎴栧凡鏈夐」鐩殑灏忔敼鍔ㄣ€?---

# java-project-init

## 鏍稿績瀹氫綅

鏈?skill 鏄噸鍨嬨€佷綆棰戙€佸己绾︽潫鐨?Java 椤圭洰鍒濆鍖?skill銆?
瀹冭礋璐ｅ湪椤圭洰璧锋闃舵寤虹珛宸ョ▼楠ㄦ灦鍜屽熀纭€绾﹀畾锛岃鍚庣画寮€鍙戞湁绋冲畾鐨勭洰褰曠粨鏋勩€佹瀯寤烘柟寮忓拰鍚姩鏂瑰紡銆傚畠涓嶈礋璐ｆ棩甯稿姛鑳藉紑鍙戙€?
榛樿鎶€鏈爤锛?
- JDK 17
- Spring Boot 3.4.5
- Maven 3.13.0
- Maven 澶氭ā鍧?- Mybatis-plus 3.5.12
- MyBatis-Plus 3.5.12+ 将 `PaginationInnerInterceptor` 移到了独立模块 `mybatis-plus-jsqlparser`。初始化时必须在父 POM 的 `dependencyManagement` 中声明该依赖，并在 `xxx-dao` 模块的 `<dependencies>` 中显式引入。
- Postgresql 42.2.25
- Flyway
- SpringDoc OpenAPI 2.8.x
- Lombok 1.18.x

## 纭€х害鏉?
瑙﹀彂鏈?skill 鍚庡繀椤婚伒瀹堜互涓嬬害鏉燂細

- 蹇呴』鎸?Maven 澶氭ā鍧楃粨鏋勫垵濮嬪寲椤圭洰锛屼笉寰楅€€鍖栦负鍗曟ā鍧?Spring Boot 椤圭洰銆?- 蹇呴』浣跨敤 MyBatis-Plus mapper 灞傦紝涓嶅緱鏀圭敤 JPA銆丠ibernate Repository 鎴?Spring Data Repository銆?- 蹇呴』浠?PostgreSQL 浣滀负榛樿鏁版嵁搴撻┍鍔紝涓嶅緱涓轰簡婕旂ず鏂逛究鏀圭敤 H2銆?- 蹇呴』浣跨敤 Flyway 绠＄悊鏁版嵁搴撹縼绉荤洰褰曪紝涓嶅緱鎶婅縼绉昏剼鏈斁鍦ㄥ崟妯″潡鏍硅祫婧愮洰褰曘€?- 蹇呴』浣跨敤 SpringDoc OpenAPI 2.x 鐢熸垚鎺ュ彛鏂囨。锛汼pring Boot 3.x 椤圭洰涓嶅緱寮曞叆 SpringFox銆?- 蹇呴』鍦ㄧ埗 POM 涓粺涓€绠＄悊 Lombok 鐗堟湰锛屽苟鍦ㄩ渶瑕?Lombok 娉ㄨВ鐨勫瓙妯″潡涓互 `provided` scope 寮曞叆銆?- 蹇呴』鍒濆鍖栫粺涓€鍝嶅簲浣?`Result<T>`銆佺姸鎬佺爜鏋氫妇 `ResultCode`銆佷笟鍔″紓甯稿拰鍏ㄥ眬寮傚父澶勭悊楠ㄦ灦銆?- 蹇呴』鍙垱寤?`xxx-web` 涓€涓?Spring Boot 鍚姩妯″潡锛沗xxx-job` 涓嶅緱鍖呭惈鐙珛鍚姩绫汇€?- 蹇呴』鍏堣鍙?`references/multi-module-structure.md`銆乣references/maven-pom-rules.md`銆乣references/config-files.md`銆乣references/api-docs-and-response.md`銆乣references/readme-template.md` 鍜?`references/gitignore-rules.md`锛屽啀寮€濮嬪垱寤洪」鐩鏋躲€?- 濡傛灉鐢ㄦ埛鍦ㄧ┖椤圭洰涓洿鎺ヨ姹傚疄鐜颁笟鍔″姛鑳斤紝搴斿厛鎸夋湰 skill 寤虹珛鍚堣楠ㄦ灦锛屽啀鍦ㄨ楠ㄦ灦鍐呭疄鐜板姛鑳斤紱涓嶅緱涓轰簡蹇€熷畬鎴愪笟鍔″姛鑳借嚜琛岄€夋嫨鍏朵粬鎶€鏈爤銆?
## 瑙﹀彂杈圭晫

鍙湪浠ヤ笅鍦烘櫙浣跨敤锛?
- 鐢ㄦ埛鏄庣‘瑕佹眰鍒濆鍖?Java 椤圭洰銆丼pring Boot 椤圭洰鎴?Maven 椤圭洰銆?- 褰撳墠浠撳簱鏄┖椤圭洰銆佹帴杩戠┖椤圭洰锛屾垨鍙湁灏戦噺鍒濆鍖栨枃浠躲€?- 鐢ㄦ埛瑕佹眰寤虹珛椤圭洰绾у伐绋嬭鑼冦€佸妯″潡缁撴瀯銆佺洰褰曠粨鏋勩€佸熀纭€渚濊禆銆佸惎鍔ㄩ厤缃垨鏁版嵁搴撹縼绉昏鑼冦€?- 鐢ㄦ埛瑕佹眰鎶婁竴涓湭鎴愬舰椤圭洰鏁寸悊涓烘爣鍑?Spring Boot Maven 澶氭ā鍧楅鏋躲€?
涓嶈鍦ㄤ互涓嬪満鏅娇鐢細

- 鍙槸鏂板涓€涓帴鍙ｃ€佸疄浣撱€丏TO銆丼ervice 鎴?Controller銆?- 鍙槸淇 bug銆佽皟鏁翠笟鍔￠€昏緫鎴栧仛灏忚寖鍥撮噸鏋勩€?- 鍙槸鏁寸悊 Java 浠ｇ爜椋庢牸銆?- 鍙槸淇敼宸叉湁椤圭洰涓殑灏戦噺閰嶇疆銆?- 椤圭洰宸茬粡鏈夋槑纭伐绋嬭鑼冿紝涓旂敤鎴锋病鏈夎姹傞噸鏂板垵濮嬪寲銆?
## 鍒濆鍖栨祦绋?
1. 鍏堝垽鏂」鐩槸鍚﹀浜庡垵濮嬪寲闃舵銆?2. 璇嗗埆鐜版湁鏋勫缓宸ュ叿銆丣ava 鐗堟湰銆佸寘鍚嶅拰鐩綍缁撴瀯銆?3. 鑻ラ」鐩凡瀛樺湪绾﹀畾锛屼紭鍏堟部鐢紱鑻ユ病鏈夌害瀹氾紝鎸夋湰 skill 鐨勯粯璁ょ害瀹氬缓绔嬮鏋躲€?4. 璇诲彇 `references/multi-module-structure.md`锛屾寜澶氭ā鍧楃粨鏋勩€佹ā鍧楄亴璐ｃ€佷緷璧栬鍒欏拰鐩綍缁撴瀯鍒涘缓楠ㄦ灦銆?5. 璇诲彇 `references/maven-pom-rules.md`锛岄厤缃牴 POM銆佸瓙妯″潡 POM銆佷緷璧栫鐞嗗拰鎻掍欢绠＄悊銆?6. 璇诲彇 `references/config-files.md`锛屽垱寤?`application.yml` 涓?`application-dev.yml`銆?7. 璇诲彇 `references/api-docs-and-response.md`锛屽垱寤?SpringDoc OpenAPI 閰嶇疆銆佺粺涓€鍝嶅簲浣撱€佺姸鎬佺爜鏋氫妇銆佷笟鍔″紓甯稿拰鍏ㄥ眬寮傚父澶勭悊楠ㄦ灦銆?8. 璇诲彇 `references/readme-template.md` 鍜?`references/gitignore-rules.md`锛岃ˉ鍏?README銆乣.gitignore` 鍜屽繀瑕佺殑鍚姩璇存槑銆?9. 楠岃瘉椤圭洰鑷冲皯鍏峰鏋勫缓鍜屽惎鍔ㄧ殑娓呮櫚璺緞銆?10. 鍒濆鍖栧畬鎴愬悗锛屽彲浣跨敤 `java-style-guide-zh` 瀵规柊澧?Java 婧愮爜鍋氳交閲忔敹灏俱€?
## 鍙傝€冩枃浠?
鎸夊垵濮嬪寲鍐呭璇诲彇瀵瑰簲鍙傝€冩枃浠讹細

- `references/multi-module-structure.md`锛歁aven 妯″潡娓呭崟銆佹ā鍧楄亴璐ｃ€佷緷璧栬鍒欍€佺洰褰曠粨鏋勫拰鍒濆鍖栦骇鐗┿€?- `references/maven-pom-rules.md`锛氭牴 POM銆佸瓙妯″潡 POM銆佸懡鍚嶈鍒欍€佷緷璧栫鐞嗗拰鎻掍欢绠＄悊銆?- `references/config-files.md`锛歚application.yml`銆乣application-dev.yml`銆佹晱鎰熼厤缃拰鏈€灏忓惎鍔ㄩ厤缃€?- `references/api-docs-and-response.md`锛歋pringDoc OpenAPI銆佺粺涓€鍝嶅簲浣撱€佺姸鎬佺爜鏋氫妇銆佷笟鍔″紓甯稿拰鍏ㄥ眬寮傚父澶勭悊銆?- `references/readme-template.md`锛歊EADME 蹇呭绔犺妭鍜屽惎鍔ㄨ鏄庛€?- `references/gitignore-rules.md`锛欽ava銆丮aven銆両DE銆佹棩蹇楀拰鏈湴鐜蹇界暐瑙勫垯銆?
涓?`SKILL.md` 涓嶉噸澶嶇淮鎶ゅ弬鑰冩枃浠剁粏鑺傦紝閬垮厤瑙勫垯鍒嗘暎銆?
## Maven 涓?Spring Boot 绾﹀畾

- 榛樿浣跨敤 Maven 鏋勫缓銆?- 鏍?`pom.xml` 鍚屾椂浣滀负鐖?POM 鍜岃仛鍚?POM锛屽彧鐢ㄤ簬缁熶竴妯″潡鑱氬悎銆佺増鏈鐞嗐€佷緷璧栫鐞嗗拰鎻掍欢閰嶇疆锛屼笉鍖呭惈涓氬姟浠ｇ爜銆?- 鍚勬ā鍧?`pom.xml` 鍙０鏄庢湰妯″潡蹇呴渶渚濊禆锛岄伩鍏嶆墍鏈変緷璧栭兘鍫嗗湪鐖?POM銆?- 渚濊禆淇濇寔鏈€灏忓寲锛屽彧鍔犲叆椤圭洰鍚姩闇€瑕佺殑渚濊禆銆?- 闇€瑕?Web 鑳藉姏鏃跺姞鍏?`spring-boot-starter-web`锛涙病鏈?Web 闇€姹傛椂涓嶈榛樿鍔犲叆銆?- `xxx-web` 榛樿寮曞叆 `springdoc-openapi-starter-webmvc-ui`锛岀増鏈敱鐖?POM 鐨?`springdoc-openapi.version` 绠＄悊锛沇ebFlux 椤圭洰鎵嶆浛鎹负 `springdoc-openapi-starter-webflux-ui`銆?- 涓嶅紩鍏?`springfox-swagger2`銆乣springfox-boot-starter` 鎴栧叾浠?SpringFox 渚濊禆銆?- Lombok 鍙綔涓虹紪璇戞湡渚濊禆浣跨敤锛宻cope 蹇呴』涓?`provided`锛屼笉寰楁墦鍖呰繘鏈€缁堜骇鐗┿€?- XXL-JOB 鎵ц鍣ㄩ厤缃斁鍦?`xxx-web`锛岀敱 `xxx-web` 闅忓悓 Web 鏈嶅姟涓€璧峰惎鍔紱`xxx-job` 鍙彁渚涗换鍔″鐞嗕唬鐮併€?- 闇€瑕佹暟鎹闂椂鍐嶅姞鍏ュ搴?starter 鎴栭┍鍔紝涓嶆彁鍓嶅爢渚濊禆銆?- 浣跨敤 PostgreSQL 鏃讹紝榛樿鍔犲叆 Flyway 鐩稿叧渚濊禆锛屽苟浼樺厛浣跨敤 Spring Boot 绠＄悊鐨勪緷璧栫増鏈€?- 榛樿鎻愪緵鍩虹 `application.yml`锛屽彧鐢ㄤ簬鎸囧畾鍚敤 `dev` profile銆?- 榛樿鏂板 `application-dev.yml`锛屽彧鏀鹃」鐩惎鍔ㄦ墍闇€鐨勬渶灏忛厤缃€?- Maven Wrapper 鍙紭鍏堟坊鍔狅紱濡傛灉鏈坊鍔狅紝搴斿湪杈撳嚭涓鏄庝娇鐢ㄦ湰鏈?Maven銆?
## Flyway 鏁版嵁搴撹縼绉荤害瀹?
鍒濆鍖栨秹鍙婃暟鎹簱鏃讹紝榛樿浣跨敤 Flyway 绠＄悊鏁版嵁搴撶粨鏋勫彉鏇淬€?
绾﹀畾锛?
- 杩佺Щ鑴氭湰榛樿鏀惧湪 `xxx-dao/src/main/resources/db/migration/`銆?- 鑴氭湰鍛藉悕浣跨敤 `V鐗堟湰鍙穇_鎻忚堪.sql`锛屼緥濡?`V1__init_schema.sql`銆?- 鐗堟湰鍙峰彧閫掑锛屼笉淇敼宸插彂甯冩垨宸叉墽琛岃繃鐨勮縼绉昏剼鏈€?- 鍒濆鍖栭樁娈靛彲浠ュ垱寤虹┖杩佺Щ鐩綍锛涘彧鏈夌敤鎴锋槑纭粰鍑轰笟鍔¤〃缁撴瀯鏃讹紝鎵嶇敓鎴愬叿浣撳缓琛ㄨ剼鏈€?- 涓嶆妸涓氬姟瀛楁銆佺储寮曘€佺害鏉熷拰鍒濆鍖栨暟鎹嚟绌哄啓鍏ヨ縼绉昏剼鏈€?- `application-dev.yml` 涓繚鐣?Flyway 鐨勫熀纭€閰嶇疆浣嶇疆锛屽叿浣撴暟鎹簱杩炴帴淇℃伅鎸夐」鐩幆澧冨～鍐欍€?- 鏁版嵁搴撶粨鏋勮璁″拰杩佺Щ鑴氭湰鍐呭瀹℃煡涓嶅湪鏈?skill 娣卞叆灞曞紑锛屽彲浜ょ粰鍚庣画鎸佷箙鍖栨垨鏁版嵁搴撹縼绉荤被 skill銆?
## 鏋勫缓涓庡惎鍔ㄧ害瀹?
鍒濆鍖栧悗搴斿叿澶囨竻鏅扮殑鏋勫缓鍜屽惎鍔ㄨ矾寰勶細

- 鑷冲皯鑳借繍琛?`mvn package` 鎴栨槑纭鏄庢瀯寤哄懡浠ゃ€?- README 涓簲璇存槑甯哥敤鍛戒护锛屼緥濡傛瀯寤哄拰鍚姩銆?- 鍙惎鍔?`xxx-web` 涓€涓?Spring Boot 鏈嶅姟锛沊XL-JOB Executor 闅?`xxx-web` 鍚姩銆?- 鍒濆鍖栫洰鏍囦互鈥滆兘鏋勫缓銆佽兘鍚姩銆佺粨鏋勬竻鏅扳€濅负涓汇€?- 涓嶉粯璁ょ粦瀹氬叿浣?formatter銆乴inter銆丆heckstyle 鎴?Spotless锛岄櫎闈炵敤鎴锋槑纭姹傘€?
## 鍒濆鍖栦骇鐗?
鎸?`references/multi-module-structure.md` 鍒涘缓澶氭ā鍧?POM銆佹ā鍧楁簮鐮佺洰褰曘€乣application.yml`銆乣application-dev.yml` 鍜?Flyway 杩佺Щ鐩綍銆?
鍚屾椂鎸夐」鐩渶瑕佽ˉ鍏咃細

- `.gitignore`
- `README.md`
- SpringDoc OpenAPI 閰嶇疆绫?- 缁熶竴鍝嶅簲浣撱€佺姸鎬佺爜鏋氫妇銆佷笟鍔″紓甯稿拰鍏ㄥ眬寮傚父澶勭悊鍣?- Maven Wrapper锛屾垨璇存槑鏈坊鍔?
涓嶈鍒涘缓锛?
- 鏃犱笟鍔℃剰涔夌殑绌?Controller銆丼ervice銆丮apper銆丏TO 鎴?Entity銆?- `xxx-job` 涓殑鐙珛 Spring Boot 鍚姩绫汇€?- 涓庡綋鍓嶉」鐩棤鍏崇殑鏁版嵁搴撱€佺紦瀛樸€佹秷鎭槦鍒椼€佸畨鍏ㄦ鏋朵緷璧栥€?- 娌℃湁涓氬姟渚濇嵁鐨?Flyway 寤鸿〃鑴氭湰鎴栧垵濮嬪寲鏁版嵁鑴氭湰銆?- 澶ч噺绀轰緥浠ｇ爜锛岄櫎闈炵敤鎴锋槑纭姹傜敓鎴?demo銆?
## 涓?java-style-guide-zh 鐨勫叧绯?
`java-project-init` 璐熻矗寤虹珛椤圭洰绾ц鍒欏拰宸ョ▼楠ㄦ灦銆?
`java-style-guide-zh` 璐熻矗鏃ュ父 Java 浠ｇ爜淇敼鍚庣殑杞婚噺椋庢牸鏀跺熬銆?
鍒濆鍖栧畬鎴愬悗锛屽鏋滄柊澧炰簡 Java 婧愮爜锛屽彲浠ュ啀浣跨敤 `java-style-guide-zh` 妫€鏌ュ懡鍚嶃€佹帓鐗堛€佹敞閲娿€佹枃浠剁粍缁囥€佹垚鍛橀『搴忋€佸彲璇绘€у拰杞婚噺浠ｇ爜寮傚懗銆?
## 杈撳嚭瑕佹眰

浣跨敤鏈?skill 瀹屾垚鍒濆鍖栨椂锛屽簲璇存槑锛?
- 閫夋嫨鐨?Java銆丼pring Boot 鍜?Maven 绾﹀畾銆?- 鍒涘缓鎴栬皟鏁翠簡鍝簺 Maven 妯″潡鍜屽伐绋嬮鏋躲€?- 娣诲姞浜嗗摢浜涘熀纭€渚濊禆銆?- 鏄惁娣诲姞 SpringDoc OpenAPI锛屼互鍙?Swagger UI銆丣SON銆乊AML 鏂囨。璁块棶鍦板潃銆?- 鏄惁娣诲姞 Lombok 鐗堟湰绠＄悊锛屼互鍙婂摢浜涙ā鍧楁寜闇€寮曞叆浜?Lombok銆?- 鏄惁鍒涘缓缁熶竴鍝嶅簲浣撱€佺姸鎬佺爜鏋氫妇銆佷笟鍔″紓甯稿拰鍏ㄥ眬寮傚父澶勭悊楠ㄦ灦銆?- 鏄惁鍚敤 Flyway锛屼互鍙婅縼绉昏剼鏈洰褰曞拰鍛藉悕绾﹀畾銆?- 濡備綍杩愯鏋勫缓鍛戒护锛屼互鍙婂浣曞惎鍔ㄥ敮涓€鐨?`xxx-web` 鏈嶅姟銆?- 鏄惁娣诲姞 Maven Wrapper锛涘鏋滄病鏈夛紝璇存槑鍘熷洜銆?- 鍝簺鍐呭琚埢鎰忓欢鍚庯紝渚嬪鏁版嵁搴撱€佸畨鍏ㄣ€佺紦瀛樸€丆I 鎴栦唬鐮佹牸寮忓寲宸ュ叿銆?
