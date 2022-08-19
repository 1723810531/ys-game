package com.wzp.ys.model;

import lombok.Data;

@Data
public class Relics {
    private String relicsNum;//名称编号
    private String name; //"名称"
    private String partsNum;//部位编号
    private String parts; //"部位"
    private String atk; //"攻击力"
    private String def; //"防御力"
    private String hp; //"生命值"
    private String atks; //"百分比攻击"
    private String defs; //"百分比防御"
    private String hps; //"百分比生命"
    private String em; //"元素精通"
    private String ec; //"元素充能效率"
    private String hit; //"暴击率"
    private String fatk; //"暴击伤害"
    private String aps; //"元素伤害加成"
    private String ads; //"物理伤害加成"
    private String tmt; //"治疗加成"
    private int page;
    private int pageSize;
}
