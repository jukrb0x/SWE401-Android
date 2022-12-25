import { isArray } from "@tsed/core";
import { deserialize } from "@tsed/json-mapper";
import { Injectable, Inject } from "@tsed/di";
import { PrismaService } from "../services/PrismaService";
import { Prisma, Activity } from "../client";
import { ActivityModel } from "../models";

@Injectable()
export class ActivitiesRepository {
  @Inject()
  protected prisma: PrismaService;

  get collection() {
    return this.prisma.activity
  }

  get groupBy() {
    return this.collection.groupBy.bind(this.collection)
  }

  protected deserialize<T>(obj: null | Activity | Activity[]): T {
    return deserialize<T>(obj, { type: ActivityModel, collectionType: isArray(obj) ? Array : undefined })
  }

  async findUnique(args: Prisma.ActivityFindUniqueArgs): Promise<ActivityModel | null> {
    const obj = await this.collection.findUnique(args);
    return this.deserialize<ActivityModel | null>(obj);
  }

  async findFirst(args: Prisma.ActivityFindFirstArgs): Promise<ActivityModel | null> {
    const obj = await this.collection.findFirst(args);
    return this.deserialize<ActivityModel | null>(obj);
  }

  async findMany(args?: Prisma.ActivityFindManyArgs): Promise<ActivityModel[]> {
    const obj = await this.collection.findMany(args);
    return this.deserialize<ActivityModel[]>(obj);
  }

  async create(args: Prisma.ActivityCreateArgs): Promise<ActivityModel> {
    const obj = await this.collection.create(args);
    return this.deserialize<ActivityModel>(obj);
  }

  async update(args: Prisma.ActivityUpdateArgs): Promise<ActivityModel> {
    const obj = await this.collection.update(args);
    return this.deserialize<ActivityModel>(obj);
  }

  async upsert(args: Prisma.ActivityUpsertArgs): Promise<ActivityModel> {
    const obj = await this.collection.upsert(args);
    return this.deserialize<ActivityModel>(obj);
  }

  async delete(args: Prisma.ActivityDeleteArgs): Promise<ActivityModel> {
    const obj = await this.collection.delete(args);
    return this.deserialize<ActivityModel>(obj);
  }

  async deleteMany(args: Prisma.ActivityDeleteManyArgs) {
    return this.collection.deleteMany(args)
  }

  async updateMany(args: Prisma.ActivityUpdateManyArgs) {
    return this.collection.updateMany(args)
  }

  async aggregate(args: Prisma.ActivityAggregateArgs) {
    return this.collection.aggregate(args)
  }
}
