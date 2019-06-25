package spring.sercurity.le.service;

import java.util.List;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.stereotype.Service;
import spring.sercurity.le.domain.Spittle;

/**
 * @author Isen
 * @date 19-6-17 下午10:29
 * @since 1.0
 */
@Service
public class SpittleService {

//    @Secured({"ROLE_SPITTER", "ROLE_ADMIN"})
//    public void addSpittle(Spittle spittle){
//
//    }

//    @RolesAllowed({"ROLE_SPITTER", "ROLE_ADMIN"})
//    public void addSpittle(Spittle spittle){
//
//    }

    @PreAuthorize("hasRole('ROLE_SPITTER') and #spittle.text.length() < 140 or hasRole('ROLE_PREMIUM')")
    public void addSpittle(Spittle spittle){

    }

    @PostAuthorize("returnObject.spitter.username = principal.username")
    public Spittle getSpittleById(long id){
        return null;
    }

    @PreAuthorize("hasAnyRole('ROLE_SPITTER', 'ROLE_ADMIN')")
    //管理员能够看到所有攻击性的Spittle,非管理员只能看到属于自己的Spittle。
    @PostFilter("hasRole('ROLE_ADMIN') || filterObject.spitter.username == principal.username")
    public List<Spittle> getOffensiveSplittles(){
        return null;
    }

//    @PreAuthorize("hasAnyRole('ROLE_SPITTER', 'ROLE_ADMIN')")
//    //管理员能够删除所有spittles,非管理员只能删除自己的spittle。
//    @PreFilter("hasRole('ROLE_ADMIN') || filterObject.spitter.username == principal.username")
//    public void deleteSplittles(List<Spittle> spittles){
//
//    }

    @PreAuthorize("hasAnyRole('ROLE_SPITTER', 'ROLE_ADMIN')")
    //管理员能够删除所有spittles,非管理员只能删除自己的spittle。
    @PreFilter("hasPermission(filterObject, 'delete')")
    public void deleteSplittles(List<Spittle> spittles){

    }
}
