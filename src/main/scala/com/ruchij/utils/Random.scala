package com.ruchij.utils

import java.util.UUID

import com.github.javafaker.Faker
import com.ruchij.models.User

trait Random[+A] {
  self =>

  def flatMap[B](f: A => Random[B]): Random[B] = () => f(self.generate()).generate()

  def map[B](f: A => B): Random[B] = () => f(self.generate())

  def generate(): A
}

object Random {
  def apply[A](implicit random: Random[A]): Random[A] = random

  def lift[A](value: => A): Random[A] = () => value

  def combine[A](randoms: Random[A]*): Random[A] =
    randomInt.flatMap(index => randoms(index % randoms.length))

  val faker: Faker = Faker.instance()
  import faker._

  implicit val randomBoolean: Random[Boolean] = lift(util.Random.nextBoolean())

  implicit val randomInt: Random[Int] = lift(math.abs(util.Random.nextInt()))

  implicit val randomUuid: Random[UUID] = lift(UUID.randomUUID())

  val quotes: Random[String] =
    combine(lift(rickAndMorty().quote()), lift(gameOfThrones().quote()), lift(chuckNorris().fact()))

  implicit def randomOption[A](implicit random: Random[A]): Random[Option[A]] =
    randomBoolean.flatMap(booleanValue => if (booleanValue) random.map(Some.apply) else lift(None))

  implicit val randomUser: Random[User] =
    for {
      uuid <- Random[UUID]
      username <- lift(name().username())
      firstName <- lift(name().firstName())
      lastName <- lift(name().lastName())
      email <- lift(internet().emailAddress())
    }
    yield User(uuid, username, firstName, lastName, email)
}
