package com.nutritionalsupplements.controller;

import com.nutritionalsupplements.entity.Supplement;
import com.nutritionalsupplements.service.Parser;
import com.nutritionalsupplements.service.SupplementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/supplement_parser")
public class SupplementParserController {

    @Autowired
    private Parser parser;

    @Autowired
    private SupplementService supplementService;

    @GetMapping
    public String showPage() {
        return "parser/supplement_parser";
    }

    @ResponseBody
    @PostMapping
    public String addSupplement(@RequestParam(name = "searchString") String searchString) {
        List<Supplement> supplements = parser.parseEPage(searchString);
        if (supplements != null) {
            String result = "<h1>Добавки были найдены и добавлены в базу даных<br></h1>";
            for (Supplement supplement : supplements) {
                supplementService.saveSupplement(supplement);
                result += supplement.getECod() + " - " + supplement.getName() + "<br><br>";
            }
            return result;
        } else {
            return "<H1>Этой добавки нет на сайте dobavkam.net</H1>";
        }
    }

    @ResponseBody
    @PostMapping("/parse_all")
    public String addAllSupplements() {
        List<Supplement> supplements = parser.parseEPage("100\t101\t102\t103\t104\t105\t106\t107\t108\t109\t110\t111\t112\t113\t114\t115\t116\t117\t118\t119\t120\t121\t122\t123\t124\t125\t126\t127\t128\t129\t130\t131\t132\t133\t134\t135\t136\t137\t138\t139\t140\t141\t142\t143\t144\t145\t146\t147\t148\t149\t150\t151\t152\t153\t154\t155\t156\t157\t158\t159\t160\t161\t162\t163\t164\t165\t166\t167\t168\t169\t170\t171\t172\t173\t174\t175\t176\t177\t178\t179\t180\t181\t182\t183\t184\t185\t186\t187\t188\t189\t190\t191\t192\t193\t194\t195\t196\t197\t198\t199\t200\t201\t202\t203\t204\t205\t206\t207\t208\t209\t210\t211\t212\t213\t214\t215\t216\t217\t218\t219\t220\t221\t222\t223\t224\t225\t226\t227\t228\t229\t230\t231\t232\t233\t234\t235\t236\t237\t238\t239\t240\t241\t242\t243\t244\t245\t246\t247\t248\t249\t250\t251\t252\t253\t254\t255\t256\t257\t258\t259\t260\t261\t262\t263\t264\t265\t266\t267\t268\t269\t270\t271\t272\t273\t274\t275\t276\t277\t278\t279\t280\t281\t282\t283\t284\t285\t286\t287\t288\t289\t290\t291\t292\t293\t294\t295\t296\t297\t298\t299\t300\t301\t302\t303\t304\t305\t306\t307\t308\t309\t310\t311\t312\t313\t314\t315\t316\t317\t318\t319\t320\t321\t322\t323\t324\t325\t326\t327\t328\t329\t330\t331\t332\t333\t334\t335\t336\t337\t338\t339\t340\t341\t342\t343\t344\t345\t346\t347\t348\t349\t350\t351\t352\t353\t354\t355\t356\t357\t358\t359\t360\t361\t362\t363\t364\t365\t366\t367\t368\t369\t370\t371\t372\t373\t374\t375\t376\t377\t378\t379\t380\t381\t382\t383\t384\t385\t386\t387\t388\t389\t390\t391\t392\t393\t394\t395\t396\t397\t398\t399\t400\t401\t402\t403\t404\t405\t406\t407\t408\t409\t410\t411\t412\t413\t414\t415\t416\t417\t418\t419\t420\t421\t422\t423\t424\t425\t426\t427\t428\t429\t430\t431\t432\t433\t434\t435\t436\t437\t438\t439\t440\t441\t442\t443\t444\t445\t446\t447\t448\t449\t450\t451\t452\t453\t454\t455\t456\t457\t458\t459\t460\t461\t462\t463\t464\t465\t466\t467\t468\t469\t470\t471\t472\t473\t474\t475\t476\t477\t478\t479\t480\t481\t482\t483\t484\t485\t486\t487\t488\t489\t490\t491\t492\t493\t494\t495\t496\t497\t498\t499\t500\t501\t502\t503\t504\t505\t506\t507\t508\t509\t510\t511\t512\t513\t514\t515\t516\t517\t518\t519\t520\t521\t522\t523\t524\t525\t526\t527\t528\t529\t530\t531\t532\t533\t534\t535\t536\t537\t538\t539\t540\t541\t542\t543\t544\t545\t546\t547\t548\t549\t550\t551\t552\t553\t554\t555\t556\t557\t558\t559\t560\t561\t562\t563\t564\t565\t566\t567\t568\t569\t570\t571\t572\t573\t574\t575\t576\t577\t578\t579\t580\t581\t582\t583\t584\t585\t586\t587\t588\t589\t590\t591\t592\t593\t594\t595\t596\t597\t598\t599\t600\t601\t602\t603\t604\t605\t606\t607\t608\t609\t610\t611\t612\t613\t614\t615\t616\t617\t618\t619\t620\t621\t622\t623\t624\t625\t626\t627\t628\t629\t630\t631\t632\t633\t634\t635\t636\t637\t638\t639\t640\t641\t642\t643\t644\t645\t646\t647\t648\t649\t650\t651\t652\t653\t654\t655\t656\t657\t658\t659\t660\t661\t662\t663\t664\t665\t666\t667\t668\t669\t670\t671\t672\t673\t674\t675\t676\t677\t678\t679\t680\t681\t682\t683\t684\t685\t686\t687\t688\t689\t690\t691\t692\t693\t694\t695\t696\t697\t698\t699\t700\t701\t702\t703\t704\t705\t706\t707\t708\t709\t710\t711\t712\t713\t714\t715\t716\t717\t718\t719\t720\t721\t722\t723\t724\t725\t726\t727\t728\t729\t730\t731\t732\t733\t734\t735\t736\t737\t738\t739\t740\t741\t742\t743\t744\t745\t746\t747\t748\t749\t750\t751\t752\t753\t754\t755\t756\t757\t758\t759\t760\t761\t762\t763\t764\t765\t766\t767\t768\t769\t770\t771\t772\t773\t774\t775\t776\t777\t778\t779\t780\t781\t782\t783\t784\t785\t786\t787\t788\t789\t790\t791\t792\t793\t794\t795\t796\t797\t798\t799\t800\t801\t802\t803\t804\t805\t806\t807\t808\t809\t810\t811\t812\t813\t814\t815\t816\t817\t818\t819\t820\t821\t822\t823\t824\t825\t826\t827\t828\t829\t830\t831\t832\t833\t834\t835\t836\t837\t838\t839\t840\t841\t842\t843\t844\t845\t846\t847\t848\t849\t850\t851\t852\t853\t854\t855\t856\t857\t858\t859\t860\t861\t862\t863\t864\t865\t866\t867\t868\t869\t870\t871\t872\t873\t874\t875\t876\t877\t878\t879\t880\t881\t882\t883\t884\t885\t886\t887\t888\t889\t890\t891\t892\t893\t894\t895\t896\t897\t898\t899\t900\t901\t902\t903\t904\t905\t906\t907\t908\t909\t910\t911\t912\t913\t914\t915\t916\t917\t918\t919\t920\t921\t922\t923\t924\t925\t926\t927\t928\t929\t930\t931\t932\t933\t934\t935\t936\t937\t938\t939\t940\t941\t942\t943\t944\t945\t946\t947\t948\t949\t950\t951\t952\t953\t954\t955\t956\t957\t958\t959\t960\t961\t962\t963\t964\t965\t966\t967\t968\t969\t970\t971\t972\t973\t974\t975\t976\t977\t978\t979\t980\t981\t982\t983\t984\t985\t986\t987\t988\t989\t990\t991\t992\t993\t994\t995\t996\t997\t998\t999\n");
        String result = "<h1>Добавки были найдены и добавлены в базу даных<br></h1>";
        for (Supplement supplement : supplements) {
            supplementService.saveSupplement(supplement);
            result += supplement.getECod() + " - " + supplement.getName() + "<br><br>";
        }
        return result;
    }
}
