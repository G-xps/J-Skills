# 澶氭ā鍧楃粨鏋勫弬鑰?
## 妯″潡娓呭崟

榛樿浣跨敤 Maven 澶氭ā鍧楃粨鏋勩€傛牴鐩綍鍙綔涓虹埗 POM 鍜岃仛鍚堝伐绋嬶紝涓嶇洿鎺ユ斁涓氬姟浠ｇ爜銆?
妯″潡鍛藉悕浣跨敤 `<artifact-prefix>-<module-role>`銆傝嫢鐢ㄦ埛娌℃湁缁欏嚭椤圭洰缂╁啓锛屽厛浠?artifactId 鎺ㄥ锛涙棤娉曟帹瀵兼椂浣跨敤 `xxx` 浣滀负鍗犱綅鍓嶇紑骞跺湪杈撳嚭涓鏄庨渶瑕佹浛鎹€?
鏍?`pom.xml` 榛樿鍖呭惈浠ヤ笅妯″潡锛?
```xml
<modules>
    <module>xxx-base-core</module>
    <module>xxx-common-api</module>
    <module>xxx-framework</module>
    <module>xxx-dao</module>
    <module>xxx-infra</module>
    <module>xxx-module-system</module>
    <module>xxx-job</module>
    <module>xxx-web</module>
</modules>
```

## 妯″潡鑱岃矗

- `xxx-base-core`锛氬熀纭€鏍稿績妯″潡锛屽瓨鏀惧叏灞€寮傚父銆佺粺涓€鍝嶅簲浣?`Result`銆佸熀纭€宸ュ叿绫汇€佸父閲忕瓑銆?- `xxx-common-api`锛氬叕鍏?API 妯″潡锛屽瓨鏀捐法妯″潡鍏变韩 DTO銆佹灇涓俱€丗eign/RPC 鎺ュ彛瀹氫箟绛夛紝鐢ㄤ簬妯″潡闂磋В鑰﹂€氫俊銆?- `xxx-framework`锛氭妧鏈鏋跺眰锛屽皝瑁?Spring Boot Starter銆丄OP 鍒囬潰銆佸畨鍏ㄩ厤缃€佹暟鎹潈闄愮瓑閫氱敤鎶€鏈兘鍔涖€?- `xxx-dao`锛氭暟鎹闂眰锛岄泦涓鐞?Entity/DO銆丮apper 鎺ュ彛鍜?XML 鏂囦欢锛屽睆钄藉簳灞傛暟鎹簱缁嗚妭锛涙寔涔呭寲璁块棶缁熶竴浣跨敤 MyBatis-Plus 鐨?mapper 灞傘€?- `xxx-infra`锛氬熀纭€璁炬柦灞傦紝灏佽 Redis銆丮Q銆丱SS銆佺煭淇°€侀偖浠剁瓑绗笁鏂逛腑闂翠欢瀵规帴銆?- `xxx-module-system`锛氱郴缁熶笟鍔℃ā鍧楋紝鎵胯浇鐢ㄦ埛銆佹潈闄愩€佽鑹层€佽璇佺瓑骞冲彴鍩虹鑳藉姏锛岄噰鐢?Controller -> Service -> dao/infra 鐨勪笁灞傜粨鏋勩€?- `xxx-job`锛氬畾鏃朵换鍔℃ā鍧楋紝绠＄悊 XXL-JOB Handler銆佷换鍔″弬鏁版ā鍨嬪拰浠诲姟缂栨帓閫昏緫锛屼笉鍖呭惈鐙珛鍚姩鍏ュ彛銆?- `xxx-web`锛氬敮涓€鍚姩妯″潡锛屽寘鍚?Spring Boot 鍚姩鍏ュ彛銆丄PI 鑱氬悎灞傚拰 XXL-JOB Executor 閰嶇疆锛屽澶栨毚闇?RESTful 鎺ュ彛骞舵壙杞藉畾鏃朵换鍔℃墽琛屽櫒銆?
## 妯″潡渚濊禆瑙勫垯

- `xxx-base-core` 淇濇寔搴曞眰骞插噣锛屼笉渚濊禆浠讳綍涓氬姟妯″潡鎴栨妧鏈ā鍧椼€?- `xxx-common-api` 鍙緷璧?`xxx-base-core`锛屼笉渚濊禆 `framework`銆乣dao`銆乣infra`銆乣module-*`銆乣job` 鎴?`web`銆?- `xxx-framework` 鍙緷璧?`xxx-base-core`锛屽繀瑕佹椂鍙緷璧?`xxx-common-api`锛涗笉寰椾緷璧?`dao`銆乣infra`銆乣module-*`銆乣job` 鎴?`web`銆?- `xxx-dao` 鍙緷璧?`xxx-base-core`锛屽繀瑕佹椂鍙緷璧?`xxx-common-api`锛涗笉寰椾緷璧?`infra`銆乣framework`銆乣module-*`銆乣job` 鎴?`web`銆?- `xxx-infra` 鍙緷璧?`xxx-base-core`锛屽繀瑕佹椂鍙緷璧?`xxx-common-api`锛涗笉寰椾緷璧?`dao`銆乣framework`銆乣module-*`銆乣job` 鎴?`web`銆?- `xxx-module-system` 鍙緷璧?`xxx-base-core`銆乣xxx-common-api`銆乣xxx-framework`銆乣xxx-dao` 鍜?`xxx-infra`銆?- `xxx-job` 鍙斁浠诲姟澶勭悊浠ｇ爜锛屽彲鎸夐渶渚濊禆 `module-*`銆乣xxx-framework`銆乣xxx-common-api` 鍜?`xxx-base-core`锛屼笉寰椾緷璧?`xxx-web`銆?- `xxx-web` 鏄敮涓€鍚姩妯″潡锛屽彲渚濊禆 `module-*`銆乣xxx-job`銆乣xxx-framework`銆乣xxx-common-api` 鍜?`xxx-base-core`銆?- 鍚姩妯″潡涓嶅緱涓轰簡鏂逛究渚濊禆鎵€鏈夋ā鍧楋紝搴旀寜瀹為檯鍏ュ彛鑱岃矗鏈€灏忎緷璧栥€?- 涓ョ妯″潡闂村惊鐜緷璧栥€?
## 鐩綍缁撴瀯

鍚勫惈浠ｇ爜妯″潡鐨勭敓浜т唬鐮侀粯璁や綅浜庯細

```text
<module>/src/main/java/<base-package>/
```

璧勬簮鏂囦欢榛樿浣嶄簬锛?
```text
<module>/src/main/resources/
```

Mapper XML 鏂囦欢榛樿浣嶄簬锛?
```text
xxx-dao/src/main/resources/mapper/<business-module>/
```

Spring Boot 椤圭洰榛樿閲囩敤浠ヤ笅鍖呯粨鏋勶紝鎸夊疄闄呴渶瑕佸垱寤猴紝閬垮厤鐢熸垚绌虹洰褰曞爢鐮岋細

```text
config/
controller/
service/
service/impl/
mapper/
domain/
dto/
exception/
common/
util/
```

绾﹀畾锛?
- 涓诲惎鍔ㄧ被鏀惧湪 `xxx-web` 鐨?base package 鏍圭洰褰曚笅銆?- 琛?璧勬簮鍨嬫湇鍔￠粯璁ら噰鐢?Service 鎺ュ彛 + Impl 瀹炵幇绫绘ā寮忥紝`service/` 鏀炬帴鍙ｏ紝`service/impl/` 鏀惧搴斿疄鐜般€?- 鑱氬悎/缂栨帓鍨嬫湇鍔″彲浠ョ洿鎺ユ斁鍦?`service/` 涓嬶紝闄ら潪瀛樺湪澶氬疄鐜般€佽法妯″潡濂戠害鎴栭渶瑕佺ǔ瀹氫唬鐞嗘墿灞曘€?- `mapper/` 鏄?MyBatis-Plus 鏁版嵁璁块棶鍏ュ彛锛涙病鏈夋寔涔呭寲闇€姹傛椂涓嶈寮鸿鍒涘缓銆?- Mapper 鍛藉悕浣跨敤 `XxxMapper`锛岀户鎵?`BaseMapper<Xxx>`锛涗笉寰楀垱寤?`XxxRepository` 鎴栦娇鐢?Spring Data Repository 妯″紡銆?- 姣忎釜 Mapper 閮藉繀椤荤敓鎴愬悓鍚?XML 鏂囦欢锛屽嵆浣垮綋鍓嶆病鏈夎嚜瀹氫箟 SQL銆?- Mapper XML 鍛藉悕浣跨敤 `XxxMapper.xml`锛屾斁鍦?`xxx-dao/src/main/resources/mapper/<business-module>/` 涓嬨€?- Mapper XML 鐨?`namespace` 蹇呴』绛変簬 Mapper 鎺ュ彛鍏ㄩ檺瀹氬悕銆?- 姣忎釜 Mapper XML 蹇呴』瀹氫箟 `BaseResultMap`锛屾樉寮忕淮鎶よ〃瀛楁鍒板疄浣撳睘鎬х殑鏄犲皠銆?- 绌?Mapper XML 涔熷繀椤讳繚鎸佸悎娉曠粨鏋勫苟鍖呭惈 `BaseResultMap`锛屼笉瑕佺暀涓嬬┖鐨?`<mapper>`銆?- `util/` 鍙斁鏃犵姸鎬併€侀€氱敤涓旈毦浠ュ綊灞炲叿浣撻鍩熺殑宸ュ叿绫汇€?- Flyway 鑴氭湰榛樿鏀惧湪 `xxx-dao/src/main/resources/db/migration/`銆?- 涓嶇敤鐩綍缁撴瀯鎺╃洊鑱岃矗闂锛涘鏉傚垎灞傚垽鏂氦缁欐灦鏋勭被 skill銆?
Mapper XML 绀轰緥锛?
```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "https://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.jskills.xxx.dao.mapper.UserMapper">
    <resultMap id="BaseResultMap" type="com.jskills.xxx.dao.domain.User">
        <id column="id" property="id"/>
        <result column="username" property="username"/>
        <result column="display_name" property="displayName"/>
        <result column="email" property="email"/>
        <result column="phone" property="phone"/>
        <result column="status" property="status"/>
        <result column="created_at" property="createdAt"/>
        <result column="updated_at" property="updatedAt"/>
        <result column="deleted" property="deleted"/>
    </resultMap>
</mapper>
```

## 鍒濆鍖栦骇鐗?
鎸夐」鐩渶瑕佸垱寤烘垨琛ュ厖锛?
- `pom.xml`
- `xxx-base-core/pom.xml`
- `xxx-common-api/pom.xml`
- `xxx-framework/pom.xml`
- `xxx-dao/pom.xml`
- `xxx-infra/pom.xml`
- `xxx-module-system/pom.xml`
- `xxx-job/pom.xml`
- `xxx-web/pom.xml`
- 鍚勫惈浠ｇ爜妯″潡鐨?`src/main/java/<base-package>/...`
- `xxx-web/src/main/resources/application.yml`
- `xxx-web/src/main/resources/application-dev.yml`
- `xxx-dao/src/main/resources/mapper/<business-module>/XxxMapper.xml`
- `xxx-dao/src/main/resources/db/migration/`
- `xxx-base-core/src/main/java/<base-package>/common/Result.java`
- `xxx-base-core/src/main/java/<base-package>/common/ResultCode.java`
- `xxx-base-core/src/main/java/<base-package>/exception/BusinessException.java`
- `xxx-framework` 鎴?`xxx-web` 涓殑 `GlobalExceptionHandler.java`
- `xxx-web` 鎴?`xxx-framework` 涓殑 `OpenApiConfig.java`

