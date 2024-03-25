package exchange.useritem.controller;

import exchange.common.result.R;
import exchange.useritem.domain.vo.req.UserItemRequest;
import exchange.useritem.manager.UserItemManager;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 物品信息表 前端控制器
 * </p>
 *
 * @author hb
 * @since 2024-02-29
 */
@RestController
@RequestMapping("/itemInfo")
public class ItemInfoController {

    private UserItemManager userItemManager;

    /**
     * 上传文件
     */
    public R<Boolean> uploadFile(@RequestParam("file") MultipartFile file, @RequestBody UserItemRequest request) {
        return R.success(userItemManager.uploadFile(file, request));
    }


}
