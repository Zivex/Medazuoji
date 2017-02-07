require.config({
  paths: {
    echarts: 'js/dist'
  }
});

require(
  [
    'echarts',
    'echarts/chart/map',
  ],
  function (ec) {

    var myChart = ec.init(document.getElementById('main')); 
    // 动态点
    var effect = {
        show: false,
        scaleSize: require('zrender/tool/env').canvasSupported ? 1 : 2,
        period: 30,             // 运动周期，无单位，值越大越慢
        color: '#fff',
        shadowColor: 'rgba(220,220,220,0.4)',
        shadowBlur : 5 
    };

    function itemStyle(idx) {
        return {
            normal: {
                color:'#fff',
                borderWidth:3,
                borderColor:['red', 'orange', 'yellow','lime','blue'][idx],
                lineStyle: {
                    //shadowColor : ['rgba(30,144,255,1)','lime'][idx], //默认透明
                    // shadowBlur: 10,
                    //shadowOffsetX: 0,
                    //shadowOffsetY: 0,
                    type: 'solid'
                }
            }
        }
    };

   var option = {
    backgroundColor: '#1b1b1b',
    color: ['red', 'orange', 'yellow','lime','blue'],
    title : {
        text: '北京热门老人公交线路图',
        subtext:'出行统计',
        x:'center',
        textStyle : {
            color: '#fff',
            fontSize: 16
        }
    },
    tooltip : {
        trigger: 'item',
        formatter: '{b}',
        textStyle : {
            fontSize: 18
        }
    },
    legend: {
        orient: 'vertical',
        x:'left',
        data:['＞2万人', '1万人~2万人','5千人~1万人','1千人~5千人','＜1千人'],
        selectedMode: 'single',
        textStyle : {
            color: '#fff',
            fontSize: 12
        }
    },
    // 辅助工具
    // toolbox: {
    //     show : true,
    //     orient : 'vertical',
    //     x: 'right',
    //     y: 'center',
    //     feature : {
    //         mark : {show: true},
    //         dataView : {show: true, readOnly: false},
    //         restore : {show: true},
    //         saveAsImage : {show: true}
    //     }
    // },
    // 柱状条
    // dataRange: {
    //     min : 0,
    //     max : 100,
    //     calculable : true,
    //     color: ['#ff3333', 'orange', 'yellow','lime','aqua'],
    //     textStyle:{
    //         color:'#fff'
    //     }
    // },
    // animationDurationUpdate: 2000, // for update animation, like legend selected. 刷新
    series : [
        {
            name: '＞2万人',
            type: 'map',
            roam: true,
            hoverable: false,
            mapType: '北京',
            selectedMode: 'single',
            itemStyle:{
                normal:{
                    label:{show:true,textStyle:{fontSize: 12}},
                    borderColor:'rgba(100,149,237,1)',
                    borderWidth:1,
                    areaStyle:{
                        color: '#1b1b1b'
                    }
                }
            },
            data:[],
            geoCoord: {
                 // 夜18路(左家庄--城南嘉园北)
                "左家庄": [116.45196,39.957556],
                "三元桥西": [116.456775,39.961437],
                "静安庄": [116.452764,39.969129],
                "西坝河": [116.445178,39.974002],
                "和平东桥南": [116.431351,39.974342],
                "和平里商场": [116.415183,39.966623],
                "和平里路口西": [116.429073,39.965481],
                "和平里北街": [116.425007,39.966266],
                "兴化路": [116.420447,39.965484],
                "和平里北街西口": [116.416271,39.96541],
                "一四二中": [116.414405,39.962441],
                "地坛西门": [116.414469,39.958964],
                "安定门内": [116.414714,39.952812],
                "方家胡同": [116.4149,39.950579],
                "交道口南": [116.414995,39.945417],
                "北兵马司": [116.415107,39.942523],
                "宽街路口南": [116.415355,39.937403],
                "大佛寺": [116.415475,39.934033],
                "美术馆北": [116.417308,39.931665],
                "灯市西口": [116.417466,39.925638],
                "新东安市场": [116.419931,39.920651],
                "王府井路口北": [116.417903,39.916023],
                "台基厂路口西": [116.416308,39.907168],
                "正义路": [116.41189,39.906967],
                "前门东": [116.408246,39.906885],
                "前门": [116.404214,39.904656],
                "大栅栏": [116.402745,39.901485],
                "煤市街南口": [116.401361,39.898127],
                "板章路": [116.39997,39.896518],
                "虎坊桥路口东": [116.393596,39.8958],
                "虎坊桥路口西": [116.38853,39.895668],
                "果子巷": [116.385206,39.895536],
                "南横街": [116.380831,39.890238],
                "自新路口北": [116.380932,39.887432],
                "开阳桥北": [116.380884,39.880055],
                "开阳桥南": [116.380671,39.872528],
                "万芳桥北": [116.378943,39.86691],
                "万芳桥南": [116.378943,39.86691],
                "嘉园二里东门": [116.377696,39.853439],
                "嘉园二里南门": [116.374833,39.851924],
                "嘉园三里北": [116.37153,39.851068],
                "嘉园三里": [116.3716,39.84836],
                "城南嘉园北": [116.371584,39.843218]
            },
            markLine : {
                smooth:true,
                symbol: ['none', 'circle'], 
                effect : effect,
                itemStyle : itemStyle(0),
                data : [
                    [{name:'左家庄'},{name:'三元桥西'}],
                    [{name:'三元桥西'},{name:'静安庄'}],
                    [{name:'静安庄'},{name:'西坝河'}],
                    [{name:'西坝河'},{name:'和平东桥南'}],
                    [{name:'和平东桥南'},{name:'和平里商场'}],
                    [{name:'和平里商场'},{name:'和平里路口西'}],
                    [{name:'和平里路口西'},{name:'和平里北街'}], 
                    [{name:'和平里北街'},{name:'兴化路'}],
                    [{name:'兴化路'},{name:'和平里北街西口'}],
                    [{name:'和平里北街西口'},{name:'一四二中'}],
                    [{name:'一四二中'},{name:'地坛西门'}],
                    [{name:'地坛西门'},{name:'安定门内'}],
                    [{name:'安定门内'},{name:'方家胡同'}],
                    [{name:'方家胡同'},{name:'交道口南'}],
                    [{name:'交道口南'},{name:'北兵马司'}],
                    [{name:'北兵马司'},{name:'宽街路口南'}],
                    [{name:'宽街路口南'},{name:'大佛寺'}],
                    [{name:'大佛寺'},{name:'美术馆北'}],
                    [{name:'美术馆北'},{name:'灯市西口'}],
                    [{name:'灯市西口'},{name:'新东安市场'}],
                    [{name:'新东安市场'},{name:'王府井路口北'}],
                    [{name:'王府井路口北'},{name:'台基厂路口西'}],
                    [{name:'台基厂路口西'},{name:'正义路'}],
                    [{name:'正义路'},{name:'前门东'}],
                    [{name:'前门东'},{name:'前门'}],
                    [{name:'前门'},{name:'大栅栏'}],
                    [{name:'大栅栏'},{name:'煤市街南口'}],
                    [{name:'煤市街南口'},{name:'板章路'}],
                    [{name:'板章路'},{name:'虎坊桥路口东'}],
                    [{name:'虎坊桥路口东'},{name:'虎坊桥路口西'}],
                    [{name:'虎坊桥路口西'},{name:'果子巷'}],
                    [{name:'果子巷'},{name:'南横街'}],
                    [{name:'南横街'},{name:'自新路口北'}],
                    [{name:'自新路口北'},{name:'开阳桥北'}],
                    [{name:'开阳桥北'},{name:'开阳桥南'}],
                    [{name:'开阳桥南'},{name:'万芳桥北'}],
                    [{name:'万芳桥北'},{name:'万芳桥南'}],
                    [{name:'万芳桥南'},{name:'嘉园二里东门'}],
                    [{name:'嘉园二里东门'},{name:'嘉园二里南门'}],
                    [{name:'嘉园二里南门'},{name:'嘉园三里北'}],
                    [{name:'嘉园三里北'},{name:'嘉园三里'}],
                    [{name:'嘉园三里'},{name:'城南嘉园北'}]
                ]
            },
            markPoint : {
                symbol : 'emptyCircle',
                symbolSize : function (v){
                    return 4 + v/10000
                },
                effect : {
                    show: true,
                    shadowBlur : 0
                },
                itemStyle:{
                    normal:{
                        label:{
                            show:false,
                            formatter : '{b}站老人数：{c}'
                        }

                    }
                },
                data : [
                    {name:'左家庄',value:2448},
                    {name:'三元桥西',value:1257},
                    {name:'静安庄',value:1407},
                    {name:'西坝河',value:1632},
                    {name:'和平东桥南',value:2507},
                    {name:'和平里商场',value:3366},
                    {name:'和平里路口西',value:3567},
                    {name:'和平里北街',value:3352},
                    {name:'兴化路',value:3277},
                    {name:'和平里北街西口',value:3271},
                    {name:'一四二中',value:3256},
                    {name:'地坛西门',value:3250},
                    {name:'安定门内',value:2802},
                    {name:'方家胡同',value:2602},
                    {name:'交道口南',value:2799},
                    {name:'北兵马司',value:2941},
                    {name:'宽街路口南',value:2650},
                    {name:'大佛寺',value:2435},
                    {name:'美术馆北',value:2463},
                    {name:'灯市西口',value:2158},
                    {name:'新东安市场',value:1731},
                    {name:'王府井路口北',value:1432},
                    {name:'台基厂路口西',value:1980},
                    {name:'正义路',value:1430},
                    {name:'前门东',value:1206},
                    {name:'前门',value:1218},
                    {name:'大栅栏',value:1431},
                    {name:'煤市街南口',value:1411},
                    {name:'板章路',value:1644},
                    {name:'虎坊桥路口东',value:3155},
                    {name:'虎坊桥路口西',value:3463},
                    {name:'果子巷',value:3322},
                    {name:'南横街',value:2656},
                    {name:'自新路口北',value:2594},
                    {name:'开阳桥北',value:2568},
                    {name:'开阳桥南',value:2454},
                    {name:'万芳桥北',value:3239},
                    {name:'万芳桥南',value:2847},
                    {name:'嘉园二里东门',value:2454},
                    {name:'嘉园二里南门',value:2100},
                    {name:'嘉园三里北',value:2095},
                    {name:'嘉园三里',value:2095},
                    {name:'城南嘉园北',value:2095}
                ]
            }
        },
        {
            name: '1万人~2万人',
            type: 'map',
            roam: true,
            hoverable: false,
            mapType: '北京',
            selectedMode: 'single',
            itemStyle:{
                normal:{
                    label:{show:true,textStyle:{fontSize: 12}},
                    borderColor:'rgba(100,149,237,1)',
                    borderWidth:1,
                    areaStyle:{
                        color: '#1b1b1b'
                    }
                }
            },
            data:[],
            geoCoord: {
                "首都机场3号航站楼": [116.621643,40.058887],
                "首都机场2号航站楼": [116.599563,40.085493],
                "首都机场1号航站楼": [116.594456,40.086138],
                "三元西桥": [116.450138,39.970841],
                "西坝河": [116.445178,39.974002],
                "安贞桥": [116.408135,39.975104],
                "马甸桥": [116.390117,39.974532],
                "北太平庄": [116.376086,39.973884],
                "蓟门桥": [116.376086,39.973884],
                "友谊宾馆": [116.326299,39.97225],
                "紫竹桥": [116.316603,39.9482],
                "航天桥": [116.316716,39.930387],
                "公主坟": [116.316793,39.913787]
            },
            markLine : {
                smooth:true,
                symbol: ['none', 'circle'], 
                effect : effect,
                itemStyle : itemStyle(1),
                data : [
                    [{name:'首都机场3号航站楼'},{name:'首都机场2号航站楼'}],
                    [{name:'首都机场2号航站楼'},{name:'首都机场1号航站楼'}],
                    [{name:'首都机场1号航站楼'},{name:'三元西桥'}],
                    [{name:'三元西桥'},{name:'西坝河'}],
                    [{name:'西坝河'},{name:'安贞桥'}],
                    [{name:'安贞桥'},{name:'马甸桥'}],
                    [{name:'马甸桥'},{name:'北太平庄'}],
                    [{name:'北太平庄'},{name:'蓟门桥'}],
                    [{name:'蓟门桥'},{name:'友谊宾馆'}],
                    [{name:'友谊宾馆'},{name:'紫竹桥'}],
                    [{name:'紫竹桥'},{name:'航天桥'}],
                    [{name:'航天桥'},{name:'公主坟'}]
                ]
            },
            markPoint : {
                symbol : 'emptyCircle',
                symbolSize : function (v){
                    return 4 + v/10000
                },
                effect : {
                    show: true,
                    shadowBlur : 0
                },
                itemStyle:{
                    normal:{
                        label:{show:false,formatter : '{b}站老人数：{c}'}
                    }
                },
                data : [
                    {name:'首都机场3号航站楼',value:47},
                    {name:'首都机场2号航站楼',value:104},
                    {name:'首都机场1号航站楼',value:200},
                    {name:'三元西桥',value:1879},
                    {name:'西坝河',value:1504},
                    {name:'安贞桥',value:3475},
                    {name:'马甸桥',value:3955},
                    {name:'北太平庄',value:2829},
                    {name:'蓟门桥',value:2718},
                    {name:'友谊宾馆',value:2379},
                    {name:'紫竹桥',value:2016},
                    {name:'航天桥',value:2339},
                    {name:'公主坟',value:2472}
                ]
            }
        },
        {
            name: '5千人~1万人',
            type: 'map',
            roam: true,
            hoverable: false,
            mapType: '北京',
            selectedMode: 'single',
            itemStyle:{
                normal:{
                    label:{show:true,textStyle:{fontSize: 12}},
                    borderColor:'rgba(100,149,237,1)',
                    borderWidth:1,
                    areaStyle:{
                        color: '#1b1b1b'
                    }
                }
            },
            data:[],
            geoCoord: {
                // 机场大巴西客站线
                "首都机场3号航站楼": [116.621643,40.058887],
                "首都机场2号航站楼": [116.599563,40.085493],
                "首都机场1号航站楼": [116.594456,40.086138],
                "朝阳公园桥": [116.496352,39.939701],
                "通惠河北路": [116.479153,39.910844],
                "永安里东街": [116.456766,39.90957],
                "广渠门": [116.450674,39.899081],
                "磁器口": [116.422927,39.899349],
                "前门大街南口": [116.406188,39.898041],
                "菜市口": [116.379957,39.895656],
                "广安门外": [116.345489,39.895591],
                "北京西站南广场": [116.328489,39.897853]
            },
            markLine : {
                smooth:true,
                symbol: ['none', 'circle'], 
                effect : effect,
                itemStyle : itemStyle(2),
                data : [
                    [{name:'首都机场3号航站楼'},{name:'首都机场2号航站楼'}],
                    [{name:'首都机场2号航站楼'},{name:'首都机场1号航站楼'}],
                    [{name:'首都机场1号航站楼'},{name:'朝阳公园桥'}],
                    [{name:'朝阳公园桥'},{name:'通惠河北路'}],
                    [{name:'通惠河北路'},{name:'永安里东街'}],
                    [{name:'永安里东街'},{name:'广渠门'}],
                    [{name:'广渠门'},{name:'磁器口'}],
                    [{name:'磁器口'},{name:'前门大街南口'}], 
                    [{name:'前门大街南口'},{name:'菜市口'}],
                    [{name:'菜市口'},{name:'广安门外'}],
                    [{name:'广安门外'},{name:'北京西站南广场'}]
                ]
            },
            markPoint : {
                symbol : 'emptyCircle',
                symbolSize : function (v){
                    return 4 + v/10000
                },
                effect : {
                    show: true,
                    shadowBlur : 0
                },
                itemStyle:{
                    normal:{
                        label:{show:false,formatter : '{b}站老人数：{c}'}
                    }
                },
                data : [
                    {name:'首都机场3号航站楼',value:46},
                    {name:'首都机场2号航站楼',value:95},
                    {name:'首都机场1号航站楼',value:215},
                    {name:'朝阳公园桥',value:636},
                    {name:'通惠河北路',value:1377},
                    {name:'永安里东街',value:962},
                    {name:'广渠门',value:2552},
                    {name:'磁器口',value:3173},
                    {name:'前门大街南口',value:1530},
                    {name:'菜市口',value:3065},
                    {name:'广安门外',value:3542},
                    {name:'北京西站南广场',value:2363}
                ]
            }
        },
        {
            name: '1千人~5千人',
            type: 'map',
            roam: true,
            hoverable: false,
            mapType: '北京',
            selectedMode: 'single',
            itemStyle:{
                normal:{
                    label:{show:true,textStyle:{fontSize: 12}},
                    borderColor:'rgba(100,149,237,1)',
                    borderWidth:1,
                    areaStyle:{
                        color: '#1b1b1b'
                    }
                }
            },
            data:[],
            geoCoord: {
                // 机场大巴方庄线(方庄--首都机场3号航站楼)
                "方庄": [116.43717,39.873286],
                "南航明珠商务酒店": [116.468759,39.911989],
                "首都机场2号航站楼": [116.600868,40.085558],
                "首都机场1号航站楼": [116.594456,40.086138],
                "首都机场3号航站楼": [116.62293,40.061781]
            },
            markLine : {
                smooth:true,
                symbol: ['none', 'circle'], 
                effect : effect,
                itemStyle : itemStyle(3),
                data : [
                    [{name:'方庄'},{name:'南航明珠商务酒店'}],
                    [{name:'南航明珠商务酒店'},{name:'首都机场2号航站楼'}],
                    [{name:'首都机场2号航站楼'},{name:'首都机场1号航站楼'}],
                    [{name:'首都机场1号航站楼'},{name:'首都机场3号航站楼'}]
                ]
            },
            markPoint : {
                symbol : 'emptyCircle',
                symbolSize : function (v){
                    return 4 + v/10000
                },
                effect : {
                    show: true,
                    shadowBlur : 0
                },
                itemStyle:{
                    normal:{
                        label:{show:false,formatter : '{b}站老人数：{c}'}
                    }
                },
                data : [
                    {name:'方庄',value:3182},
                    {name:'南航明珠商务酒店',value:830},
                    {name:'首都机场2号航站楼',value:95},
                    {name:'首都机场1号航站楼',value:204},
                    {name:'首都机场3号航站楼',value:42}
                ]
            }
        },
        {
            name: '＜1千人',
            type: 'map',
            roam: true,
            hoverable: false,
            mapType: '北京',
            selectedMode: 'single',
            itemStyle:{
                normal:{
                    label:{show:true,textStyle:{fontSize: 12}},
                    borderColor:'rgba(100,149,237,1)',
                    borderWidth:1,
                    areaStyle:{
                        color: '#1b1b1b'
                    }
                }
            },
            data:[],
            geoCoord: {
                // 南苑机场线空调(西单--南苑机场)
                "西单": [116.384118,39.913722],
                "南苑机场": [116.403467,39.797624]
            },
            markLine : {
                smooth:true,
                symbol: ['none', 'circle'], 
                effect : effect,
                itemStyle : itemStyle(4),
                data : [
                    [{name:'西单'},{name:'南苑机场'}]
                ]
            },
            markPoint : {
                symbol : 'emptyCircle',
                symbolSize : function (v){
                    return 4 + v/10000
                },
                effect : {
                    show: true,
                    shadowBlur : 0
                },
                itemStyle:{
                    normal:{
                        label:{show:false,formatter : '{b}站老人数：{c}'}
                    }
                },
                data : [
                    {name:'西单',value:1215},
                    {name:'南苑机场',value:456}
                ]
            }
        }
    ]
};

    // 为echarts对象加载数据 
    myChart.setOption(option); 
  }
);