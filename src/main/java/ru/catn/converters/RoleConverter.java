package ru.catn.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import ru.catn.core.model.Role;
import ru.catn.core.repositories.RoleRepository;

@Component
public class RoleConverter implements Converter<String, Role> {

    private RoleRepository roleRepository;

    public RoleConverter(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role convert(String source) {
        if (StringUtils.hasText(source)) {
            var role = roleRepository.findById(Integer.parseInt(source));
            if (role.isPresent()) {
                return role.get();
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
}
