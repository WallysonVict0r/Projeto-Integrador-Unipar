package br.unipar.assetinsight.security;

import br.unipar.assetinsight.entities.RolesEntity;
import br.unipar.assetinsight.entities.UsuarioEntity;
import br.unipar.assetinsight.enums.PermissoesEnum;
import br.unipar.assetinsight.repositories.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.logging.Logger;

@Configuration
public class SuperUserConfig implements CommandLineRunner {
    private static final Logger LOGGER = Logger.getLogger(SuperUserConfig.class.getName());

    @Autowired
    private UsuarioRepository userRepository;

    @Value("${assetinsight.user.super.username}")
    private String username;
    @Value("${assetinsight.user.super.password}")
    private String password;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        RolesEntity role = new RolesEntity();
        role.setPermisao(PermissoesEnum.SUPER);
        role.setId(PermissoesEnum.SUPER.getId());

        boolean isUserCadastrado = userRepository.findByListRolesContaining(role).isEmpty();

        if (isUserCadastrado) {
            LOGGER.info("Usuario Super já existe, pulando cadastro...");
        } else {
            String senhaCriptografada = new BCryptPasswordEncoder().encode(password);

            LOGGER.info("Cadastrando usuario Super...");
            UsuarioEntity user = new UsuarioEntity();
            user.setUsername(username);
            user.setPassword(senhaCriptografada);
            List<RolesEntity> roles = List.of(role);
            user.setListRoles(roles);
            userRepository.save(user);
            System.out.println("Usuario Super cadastrado com sucesso!");
        }
    }

}