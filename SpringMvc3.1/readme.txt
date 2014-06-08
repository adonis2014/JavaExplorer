--------------- Input argument. -----------------------
org.springframework.web.context.request.WebRequest
org.springframework.web.context.request.NativeWebRequest
java.util.Locale [for the current request locale, determined by the most specific locale resolver available, in effect, the configured LocaleResolver in a Servlet environment.]
java.io.InputStream / java.io.Reader
java.io.OutputStream / java.io.Writer
java.security.Principal
@PathVariable
@MatrixVariable
@RequestParam
@RequestHeader
@RequestBody
@RequestPart
@CookieValue
@ModelAttribute
HttpEntity<?>
java.util.Map / org.springframework.ui.Model / org.springframework.ui.ModelMap
org.springframework.web.servlet.mvc.support.RedirectAttributes
org.springframework.validation.Errors
org.springframework.validation.BindingResult
org.springframework.web.bind.support.SessionStatus
org.springframework.web.util.UriComponentsBuilder

----------------- Return types ----------------------------
ModelAndView
Model
Map
View
String
void
@ResponseBody
HttpEntity<?> / ResponseEntity<?>
Callable<?>
DeferredResult<?>