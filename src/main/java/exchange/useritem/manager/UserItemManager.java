package exchange.useritem.manager;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.google.common.collect.Lists;
import exchange.common.exception.ResultCode;
import exchange.common.exception.utils.AssertUtil;
import exchange.satoken.LoginHelper;
import exchange.useritem.convert.UserItemsConvert;
import exchange.useritem.domain.entity.ItemInfo;
import exchange.useritem.domain.entity.ItemLabel;
import exchange.useritem.domain.entity.UserItems;
import exchange.useritem.domain.vo.req.UserItemRequest;
import exchange.useritem.mapper.ItemInfoMapper;
import exchange.useritem.mapper.ItemLabelMapper;
import exchange.useritem.mapper.UserItemsMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/*
 * @description
 * @author hb
 * @since 2024/2/29 11:59
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class UserItemManager {

    private UserItemsMapper userItemsMapper;

    private ItemInfoMapper itemInfoMapper;

    private ItemLabelMapper itemLabelMapper;

    /**
     * 上传文件
     */
    @SneakyThrows
    @Transactional(rollbackFor = {Exception.class})
    public Boolean uploadFile(MultipartFile file, UserItemRequest request) {
        AssertUtil.isTrue(file.isEmpty(), ResultCode.FILE_CANNOT_NULL);

        String fileType = file.getContentType();
        Long fileSize = file.getSize();
        String fileName = file.getName();
        byte[] md5Bytes = DigestUtils.md5Digest(file.getInputStream());
        StringBuilder sb = new StringBuilder();
        for (byte b : md5Bytes) {
            sb.append(String.format("%02x", b));
        }
        String fileMd5 = sb.toString();

        ItemInfo itemInfo = UserItemsConvert.INSTANCE.convert(fileName, fileType, fileSize, fileMd5, request.getItemPoint());
        itemInfoMapper.insert(itemInfo);

        UserItems userItems = UserItemsConvert.INSTANCE.convert(itemInfo.getId(), LoginHelper.getUserId(), request.getDescription());
        userItemsMapper.insert(userItems);

        if (CollectionUtils.isNotEmpty(request.getItemLabel())) {
            List<ItemLabel> itemLabels = Lists.newArrayList();
            for (String labelName : request.getItemLabel()) {
                itemLabels.add(UserItemsConvert.INSTANCE.convert(itemInfo.getId(), labelName));
            }

            itemLabelMapper.insertBatch(itemLabels);
        }
        return true;
    }
}
