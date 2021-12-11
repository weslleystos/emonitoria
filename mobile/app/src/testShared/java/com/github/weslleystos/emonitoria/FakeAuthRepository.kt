package java.com.github.weslleystos.emonitoria

import com.github.weslleystos.emonitoria.domain.auth.model.AuthUser
import com.github.weslleystos.emonitoria.domain.auth.repository.AuthRepository
import com.github.weslleystos.emonitoria.domain.shared.exceptions.AuthException
import com.github.weslleystos.emonitoria.domain.shared.model.Resource
import com.github.weslleystos.emonitoria.shared.util.*

class FakeAuthRepository : AuthRepository {
    private val dataset = listOf(
        AuthUser(
            "123421", "token-123", "Test", "test@email.com", "Email", true, null
        )
    )

    override suspend fun loginWithEmailAndPassword(
        email: String,
        password: String
    ): Resource<AuthUser> {
        return when {
            !ValidatorString(email)
                .isNotEmpty()
                .isEmail()
                .isValid() -> Resource.failure(AuthException("E-mail e/ou senha inválido"))

            !ValidatorString(password)
                .isNotEmpty()
                .isGreatThan(7)
                .isLessThan(15)
                .isValid() -> Resource.failure(AuthException("E-mail e/ou senha inválido"))

            dataset.find { user -> user.email == email && password == "12345678" } == null ->
                Resource.failure(AuthException("E-mail e/ou senha inválido"))

            else -> Resource.success(
                AuthUser(
                    "123421", "token-123", "Test", email, "Email", true, null
                )
            )
        }
    }
}