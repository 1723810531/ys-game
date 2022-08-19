package com.wzp.ys.service;

import com.wzp.ys.model.RoleBasic;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Slf4j
@Service
public class InjuryExpectationService {

    public String injury(RoleBasic data) throws Exception{
        String injury = (buff(data).multiply(reaction(data)).multiply(resistance(data)).multiply(defense(data)).multiply(basic(data))).setScale(0,BigDecimal.ROUND_HALF_DOWN)+"";
        log.info("伤害期望：{}",injury);
        return injury;
    }

    //增伤区 1+所有伤害提升或提高
    public BigDecimal buff(RoleBasic data) throws Exception{
        BigDecimal buffNum = BigDecimal.ONE;
        buffNum = buffNum.add(new BigDecimal(data.getAps()).divide(new BigDecimal(100)));
        log.info("增伤区:{}",buffNum);
        return buffNum;
    }

    //元素反应区 只计算增幅反应只有该反应是乘算
    /**
     * 角色属性(1:风 2:火 3:水 4:冰 5:雷 6:岩 7:草)
     * 融化反应倍率： 冰打火=>1.5 火打冰=>2
     * 蒸发反应倍率： 火打水=>1.5 水打火=>2
     * 增幅反应提升：[精通值/(0.0036*精通值+5)]/100
     * 聚变反应： 增幅反应 * 4
     * 结晶反应：增幅反应 * 1.6
     * (1+反应提升+其他反应提升)*反应倍率
     */
    public BigDecimal reaction(RoleBasic data) throws Exception{
        BigDecimal em = new BigDecimal(data.getEm());
        BigDecimal reactionNum = BigDecimal.ONE;
        String reaction = data.getReaction();
        try{
            reactionNum.add(new BigDecimal(reaction));
        }catch (Exception e){}
        reactionNum = reactionNum.add(em.divide(new BigDecimal(0.0036).multiply(em).add(new BigDecimal(5)),10,BigDecimal.ROUND_HALF_UP).divide(new BigDecimal(100)));
//        int type = Integer.parseInt(data.getType());
        int type = 4;
        switch (type){
            case 2:
                if(true){
                    reactionNum = reactionNum.multiply(new BigDecimal(1.5));
                }else{
                    reactionNum = reactionNum.multiply(new BigDecimal(2));
                }
                break;
            case 3: reactionNum = reactionNum.multiply(new BigDecimal(2));break;
            case 4: reactionNum = reactionNum.multiply(new BigDecimal(1.5));break;
        }
        log.info("元素反应区:{}",reactionNum);
        reactionNum = BigDecimal.ONE;
        return reactionNum;
    }

    //抗性区
    public BigDecimal resistance(RoleBasic data) throws Exception{
        BigDecimal resistanceNum = BigDecimal.ONE;
        BigDecimal res = new BigDecimal(data.getRes()).divide(new BigDecimal(100));
        if(res.compareTo(new BigDecimal(75)) == 1){
            resistanceNum = BigDecimal.ONE.divide((resistanceNum.add(res.divide(new BigDecimal(100)))).multiply(new BigDecimal(4)));
        }else if(res.compareTo(BigDecimal.ZERO) == 1 && res.compareTo(new BigDecimal(75)) < 1){
            resistanceNum = BigDecimal.ONE.subtract(res);
        }else{
            resistanceNum = BigDecimal.ONE.subtract(res.divide(new BigDecimal(2)));
        }
        log.info("抗性区:{}",resistanceNum);
        return resistanceNum;
    }

    //防御区
    public BigDecimal defense(RoleBasic data) throws Exception{
        BigDecimal defenseNum = BigDecimal.ZERO;
        BigDecimal grade =  new BigDecimal(data.getGrade()).add(new BigDecimal(100));
        BigDecimal grades = new BigDecimal(data.getGrades()).add(new BigDecimal(100)).multiply(BigDecimal.ONE.subtract(new BigDecimal(data.getSunder())));
        defenseNum = grade.divide(grades.add(grade), 10,BigDecimal.ROUND_HALF_UP);
        log.info("防御区:{}",defenseNum);
        return defenseNum;
    }

    //倍率区 * 攻击区 * 暴击区
    public BigDecimal basic(RoleBasic data) throws Exception{
        BigDecimal basicNum = BigDecimal.ZERO;
        basicNum = new BigDecimal(data.getBuff()).divide(new BigDecimal(100))
                .multiply(new BigDecimal(data.getAtk()))
                .multiply(BigDecimal.ONE.add(new BigDecimal(data.getHit()).divide(new BigDecimal(100)).multiply(new BigDecimal(data.getFatk()).divide(new BigDecimal(100)))));
        log.info("倍率区 * 攻击区 * 暴击区:{}",basicNum);
        return basicNum;
    }
}
