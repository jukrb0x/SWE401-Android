import { isArray } from "@tsed/core";
import { deserialize } from "@tsed/json-mapper";
import { Injectable, Inject } from "@tsed/di";
import { PrismaService } from "../services/PrismaService";
import { Prisma, Sleep } from "../client";
import { SleepModel } from "../models";

@Injectable()
export class SleepsRepository {
  @Inject()
  protected prisma: PrismaService;

  get collection() {
    return this.prisma.sleep
  }

  get groupBy() {
    return this.collection.groupBy.bind(this.collection)
  }

  protected deserialize<T>(obj: null | Sleep | Sleep[]): T {
    return deserialize<T>(obj, { type: SleepModel, collectionType: isArray(obj) ? Array : undefined })
  }

  async findUnique(args: Prisma.SleepFindUniqueArgs): Promise<SleepModel | null> {
    const obj = await this.collection.findUnique(args);
    return this.deserialize<SleepModel | null>(obj);
  }

  async findFirst(args: Prisma.SleepFindFirstArgs): Promise<SleepModel | null> {
    const obj = await this.collection.findFirst(args);
    return this.deserialize<SleepModel | null>(obj);
  }

  async findMany(args?: Prisma.SleepFindManyArgs): Promise<SleepModel[]> {
    const obj = await this.collection.findMany(args);
    return this.deserialize<SleepModel[]>(obj);
  }

  async create(args: Prisma.SleepCreateArgs): Promise<SleepModel> {
    const obj = await this.collection.create(args);
    return this.deserialize<SleepModel>(obj);
  }

  async update(args: Prisma.SleepUpdateArgs): Promise<SleepModel> {
    const obj = await this.collection.update(args);
    return this.deserialize<SleepModel>(obj);
  }

  async upsert(args: Prisma.SleepUpsertArgs): Promise<SleepModel> {
    const obj = await this.collection.upsert(args);
    return this.deserialize<SleepModel>(obj);
  }

  async delete(args: Prisma.SleepDeleteArgs): Promise<SleepModel> {
    const obj = await this.collection.delete(args);
    return this.deserialize<SleepModel>(obj);
  }

  async deleteMany(args: Prisma.SleepDeleteManyArgs) {
    return this.collection.deleteMany(args)
  }

  async updateMany(args: Prisma.SleepUpdateManyArgs) {
    return this.collection.updateMany(args)
  }

  async aggregate(args: Prisma.SleepAggregateArgs) {
    return this.collection.aggregate(args)
  }
}
