import scalaj.http._

object Run extends App {
  def generatePath(baseUrl: String, name: String, version: String, extension: String): String = s"${baseUrl}${name}/${version}/${name}-${version}.${extension}"

  def generateUrl(name: String, version: List[String]): List[String] = {
    val baseUrl = "https://repostiroy/repo/"
    version flatMap (v => List(
      generatePath(baseUrl, name, v, "tar.gz"),
      generatePath(baseUrl, name, v, "ivy")
    ))
  }

  List(
    Package("contextlib2", List("0.6.0")),
    Package("coverage", List("4.4")),
    Package("docutils", List("0.11", "0.14")),
    Package("enum34", List("1.1.10")),
    Package("execnet", List("1.1")),
    Package("flake8", List("3.2.1", "3.8.4")),
    Package("funcsigs", List("1.0.2")),
    Package("functools32", List("3.2.3-2")),
    Package("idna", List("2.5")),
    Package("imagesize", List("1.1.0", "1.2.0")),
    Package("importlib_metadata", List("2.0.0")),
    Package("Jinja2", List("2.3", "2.10")),
    Package("MarkupSafe", List("0.23", "1.0")),
    Package("mccabe", List("0.5.0", "0.6.0")),
    Package("more-itertools", List("4.0.0")),
    Package("ordereddict", List("1.1")),
    Package("packaging", List("18.0", "20.4")),
    Package("pathlib2", List("2.2.0", "2.3.5")),
    Package("pip", List("8.1.0", "18.1")),
    Package("pluggy", List("0.7.1")),
    Package("py", List("1.4.29", "1,5,1", "1.9.0")),
    Package("py4j", List("0.10.4", "0.10.5", "0.10.9")),
    Package("pycodestyle", List("2.0.0", "2.6.0")),
    Package("pyflakes", List("0.8.1", "2.6.0")),
    Package("Pygments", List("2.0", "2.2.0")),
    Package("pypandoc", List("1.5")),
    Package("pyparsing", List("2.0.2")),
    Package("pyspark", List("2.2.1", "3.0.0", "3.0.1")),
    Package("pytest", List("2.9.0", "3.0.0", "3.10.0")),
    Package("pytest-cov", List("2.6.0")),
    Package("pytest-forked", List("1.3.0")),
    Package("pytest-runner", List("2.9", "5.2")),
    Package("pytest-xdist", List("1.24.0")),
    Package("pytz", List("2004b.2", "2018.5")),
    Package("requests", List("2.0.0", "2.19.1")),
    Package("scandir", List("1.10.0")),
    Package("setuptools", List("40.5.0", "50.3.1", "50.3.2")),
    Package("setuptools-git", List("1.2")),
    Package("setuptools_scm", List("1.15.5", "4.1.2")),
    Package("six", List("1.0.0", "1.5.0", "1.10.0", "1.13.0", "1.15.0")),
    Package("snowballstemmer", List("1.1.0", "1.2.1")),
    Package("Sphinx", List("1.8.1")),
    Package("sphinxcontrib-serializinghtml", List("1.1.4")),
    Package("sphinxcontrib-websupport", List("1.1.0", "1.2.4")),
    Package("typing", List("3.6.6", "3.7.4.3")),
    Package("urllib3", List("1.21.1")),
    Package("virtualenv", List("16.0.0", "16.1.0")),
    Package("wheel", List("0.25.0", "0.31.1")),
    Package("zipp", List("0.5.0")),
  ).foldLeft(List.empty[String])((z, c) => z concat generateUrl(c.name, c.version)).foreach( url => {
    val code = Http(url).postForm.method("DELETE").auth("user", "pass").asString.code
    println(if (code == 204) s"url remove with SUCCESS => code $code url $url" else s"url remove with ERROR => code $code url $url")
  })
}
