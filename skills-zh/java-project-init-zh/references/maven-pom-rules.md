# Maven POM 瑙勫垯

## 鍛藉悕瑙勫垯

- `groupId` 榛樿浣跨敤 `com.jskills`銆?- `artifactId` 浣跨敤灏忓啓鐭í绾匡紝渚嬪 `j-skill-demo`銆?- `base package` 浠?`com.jskills` 鍜岃鑼冨寲鍚庣殑椤圭洰鍚嶆帹瀵硷紝渚嬪 `com.jskills.jskilldemo`銆?- 妯″潡鍚嶄娇鐢?`<artifact-prefix>-<module-role>`锛屼緥濡?`demo-web`銆乣demo-dao`銆?- 涓嶅緱浣跨敤 `org.example`銆乣com.example`銆乣example`銆乣demo`銆乣sample` 杩欑被婕旂ず鍖呭悕浣滀负鏈€缁堝寘鍚嶃€?
## 鏍?POM

鏍?`pom.xml` 鍚屾椂浣滀负鐖?POM 鍜岃仛鍚?POM锛?
- `packaging` 蹇呴』涓?`pom`銆?- 鍙鐞?`modules`銆乣properties`銆乣dependencyManagement` 鍜?`pluginManagement`銆?- 涓嶅寘鍚?Java 婧愮爜锛屼笉鏀句笟鍔′緷璧栧疄鐜般€?- 缁熶竴澹版槑 JDK銆丼pring Boot銆丮yBatis-Plus銆丳ostgreSQL銆丗lyway銆丩ombok 绛夌増鏈€?- 缁熶竴澹版槑 SpringDoc OpenAPI 2.x 鐗堟湰锛汼pring Boot 3.4.5 榛樿浣跨敤 `2.8.x`锛屼笉寰椾娇鐢?SpringFox銆?- 缁熶竴澹版槑 Lombok 鐗堟湰锛岄粯璁や娇鐢ㄥ綋鍓嶇ǔ瀹氱殑 `1.18.x` 鐗堟湰锛涚ず渚嬩娇鐢?`1.18.46`銆?
绀轰緥锛?
```xml
<properties>
    <lombok.version>1.18.46</lombok.version>
</properties>

<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>${lombok.version}</version>
            <scope>provided</scope>
        </dependency>
    </dependencies>
</dependencyManagement>
```

## 瀛愭ā鍧?POM

鍚勬ā鍧?`pom.xml` 鍙０鏄庢湰妯″潡闇€瑕佺殑渚濊禆锛?
- 涓嶉噸澶嶅０鏄庡凡鐢辨牴 POM 绠＄悊鐨勭増鏈彿銆?- 涓嶆妸鎵€鏈変緷璧栧爢鍒版墍鏈夋ā鍧椼€?- 闇€瑕佷娇鐢?Lombok 娉ㄨВ鐨勫瓙妯″潡鏄惧紡寮曞叆 `org.projectlombok:lombok`锛宻cope 浣跨敤 `provided`銆?- `xxx-web` 鏄敮涓€鍙惎鍔ㄦā鍧楋紝璐熻矗 Spring Boot 鎵撳寘鍜屽惎鍔ㄥ叆鍙ｃ€?- `xxx-web` 澹版槑 `springdoc-openapi-starter-webmvc-ui`锛岀敤浜庢毚闇?Swagger UI 鍜?OpenAPI 鏂囨。銆?- `xxx-job` 涓嶅０鏄庣嫭绔嬪惎鍔ㄦ彃浠堕厤缃€?- `xxx-dao` 澹版槑 MyBatis-Plus銆丳ostgreSQL銆丗lyway 绛夋暟鎹闂浉鍏充緷璧栥€?
## 鍚姩妯″潡鎵撳寘鎻掍欢

`xxx-web/pom.xml` 蹇呴』榛樿娣诲姞 `spring-boot-maven-plugin`锛屽惁鍒欏妯″潡椤圭洰鎵撳寘鍚庣殑 jar 鍙兘鏃犳硶鐩存帴鍚姩銆?
绾﹀畾锛?
- 鍙湪 `xxx-web` 閰嶇疆 Spring Boot 鎵撳寘鎻掍欢銆?- 鏄惧紡閰嶇疆 `mainClass`锛屾寚鍚戝敮涓€ Spring Boot 鍚姩绫汇€?- 閰嶇疆 `repackage` goal锛岀敓鎴愬彲鎵ц jar銆?- 鍏朵粬闈炲惎鍔ㄦā鍧椾笉寰楅厤缃?`spring-boot-maven-plugin` 鐨?repackage銆?
绀轰緥锛?
```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
            <version>${spring-boot.version}</version>
            <configuration>
                <mainClass>com.jskills.xxx.XxxApplication</mainClass>
            </configuration>
            <executions>
                <execution>
                    <goals>
                        <goal>repackage</goal>
                    </goals>
                </execution>
            </executions>
        </plugin>
    </plugins>
</build>
```

## 渚濊禆澹版槑鍘熷垯

- 渚濊禆鎸夋ā鍧楄亴璐ｆ渶灏忓寲澹版槑銆?- 鐖?POM 绠＄増鏈紝瀛愭ā鍧楃浣跨敤銆?- 绂佹閫氳繃鐖?POM 璁╂墍鏈夋ā鍧楅殣寮忚幏寰椾笉闇€瑕佺殑渚濊禆銆?- Spring Boot 鎻掍欢鍙簲浣滅敤浜庡彲鍚姩妯″潡銆?- SpringDoc OpenAPI 鐗堟湰鍙湪鐖?POM 绠＄悊锛學eb 妯″潡鍙０鏄庝娇鐢ㄣ€?- WebFlux 椤圭洰鎵嶄娇鐢?`springdoc-openapi-starter-webflux-ui`锛涢粯璁?WebMVC 椤圭洰浣跨敤 `springdoc-openapi-starter-webmvc-ui`銆?- Lombok 鐗堟湰鍙湪鐖?POM 绠＄悊锛屽瓙妯″潡鎸夐渶澹版槑锛涗笉寰椾綔涓鸿繍琛屾椂渚濊禆鎵撳寘銆?- 濡傛灉椤圭洰鏄惧紡閰嶇疆 `maven-compiler-plugin` 鐨?`annotationProcessorPaths`锛屽繀椤绘妸 Lombok 鍔犲叆 annotation processor 璺緞锛屼繚璇佸懡浠よ鏋勫缓绋冲畾銆?
## 绂佹椤?
鍒濆鍖栭」鐩椂涓嶅緱寮曞叆浠ヤ笅鏇夸唬鎶€鏈爤锛?
- 涓嶄娇鐢?`spring-boot-starter-data-jpa`銆?- 涓嶄娇鐢?Hibernate Repository 鎴?Spring Data Repository銆?- 涓嶄娇鐢?H2 浣滀负榛樿杩愯鏁版嵁搴撱€?- 涓嶄娇鐢?`springfox-swagger2`銆乣springfox-boot-starter` 鎴栧叾浠?SpringFox 渚濊禆銆?- 涓嶆妸 Lombok 澹版槑涓洪粯璁よ繍琛屾椂渚濊禆鎴栫紪璇戜骇鐗╀緷璧栥€?- 涓嶄娇鐢?`org.example`銆乣com.example`銆乣example`銆乣demo`銆乣sample` 杩欑被婕旂ず鍖呭悕浣滀负鏈€缁?Java 鍖呭悕銆?- 涓嶄负浜嗘紨绀?CRUD 鐢熸垚鍗曟ā鍧椾緷璧栫粨鏋勩€?- 涓嶅湪闈?`xxx-web` 妯″潡閰嶇疆 Spring Boot 鍙墽琛?jar 鎵撳寘銆?
