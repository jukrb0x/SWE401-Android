import { Controller, Inject } from '@tsed/di';
import { Get, Post } from '@tsed/schema';
import { BodyParams, PathParams } from '@tsed/platform-params';
import { UserService } from '@/services/UserService';
import { UserModel } from '@/models';

@Controller('/user')
export class UserController {
    // @Get('/')
    // get() {
    //     return {
    //         date: new Date() /*.toLocaleString('en-US', { timeZone: 'Asia/Kuala_Lumpur' })*/,
    //         message: 'hello world'
    //     };
    // }

    @Inject()
    private userService: UserService;

    @Get('/login')
    async login(@PathParams('email') email: string, @PathParams('password') password: string) {
        const loggedIn = await this.userService.login(email, password);
        return loggedIn;
    }

    @Post('/register')
    register(
        @BodyParams('firstName') firstName: string,
        @BodyParams('lastName') lastName: string,
        @BodyParams('email') email: string,
        @BodyParams('password') password: string
    ) {
        const newUser = this.userService.register(firstName, lastName, email, password);
        if(newUser) {
            return newUser;
        }
    }
}
