package exchange.useritem.mapper;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import exchange.common.page.PageResult;
import exchange.framework.mybatis.core.mapper.BaseMapperX;
import exchange.framework.mybatis.core.utils.MyBatisUtils;
import exchange.satoken.LoginHelper;
import exchange.useritem.domain.entity.ItemInfo;
import exchange.useritem.domain.entity.UserItems;
import exchange.useritem.domain.vo.req.SelectMyItemRequest;
import exchange.useritem.domain.vo.req.SelectUserItemRequest;
import exchange.useritem.domain.vo.resp.UserItemResponse;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 物品信息表 Mapper 接口
 * </p>
 *
 * @author hb
 * @since 2024-02-29
 */
@Mapper
public interface ItemInfoMapper extends BaseMapperX<ItemInfo> {

    default PageResult<UserItemResponse> getMyItems(SelectMyItemRequest request) {

        MPJLambdaWrapper<ItemInfo> lambdaWrapper = new MPJLambdaWrapper<ItemInfo>()
                .innerJoin(UserItems.class, UserItems::getItemId, ItemInfo::getId)
                .selectAll(ItemInfo.class)
                .select(UserItems::getLikeNumber, UserItems::getWeakNumber, UserItems::getDownloadNumber, UserItems::getDescription)
                .orderByDesc(ItemInfo::getCreateTime);

        if (StrUtil.isNotBlank(request.getItemName())) {
            lambdaWrapper.like(ItemInfo::getItemName, request.getItemName());
        }

        if (CollectionUtils.isNotEmpty(request.getStates())) {
            lambdaWrapper.in(UserItems::getState, request.getStates());
        }

        lambdaWrapper.eq(UserItems::getUserId, LoginHelper.getUserId());

        Page<UserItemResponse> pageList = selectJoinPage(MyBatisUtils.buildPage(request), UserItemResponse.class, lambdaWrapper);
        return new PageResult<>(pageList.getRecords(), pageList.getTotal());
    }

    default PageResult<UserItemResponse> getPage(SelectUserItemRequest request) {
        MPJLambdaWrapper<ItemInfo> lambdaWrapper = new MPJLambdaWrapper<ItemInfo>()
                .innerJoin(UserItems.class, UserItems::getItemId, ItemInfo::getId)
                .selectAll(ItemInfo.class)
                .select(UserItems::getLikeNumber, UserItems::getWeakNumber, UserItems::getDownloadNumber, UserItems::getDescription)
                .orderByDesc(UserItems::getLikeNumber);

        if (StrUtil.isNotBlank(request.getItemName())) {
            lambdaWrapper.like(ItemInfo::getItemName, request.getItemName());
        }

        Page<UserItemResponse> pageList = selectJoinPage(MyBatisUtils.buildPage(request), UserItemResponse.class, lambdaWrapper);
        return new PageResult<>(pageList.getRecords(), pageList.getTotal());
    }

}
