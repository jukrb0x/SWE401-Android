import { Controller } from '@tsed/di';
import { Get } from '@tsed/schema';

@Controller('/test')
export class TestController {
    @Get('/')
    get() {
        return {
            date: new Date() /*.toLocaleString('en-US', { timeZone: 'Asia/Kuala_Lumpur' })*/,
            message: 'hello world'
        };
    }
}
