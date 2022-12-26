import { Inject, Injectable } from '@tsed/di';
import { UsersRepository } from '@/repositories';

@Injectable()
export class UserService {
    @Inject()
    private userRepo: UsersRepository;

    async login(email: string, password: string) {
        return await this.userRepo.findUnique({ where: { email } });
    }

    async register(firstName: string, lastName: string, email: string, password: string) {
        return await this.userRepo.create({
            data: {
                firstName,
                lastName,
                email,
                password
            }
        });
    }
}
